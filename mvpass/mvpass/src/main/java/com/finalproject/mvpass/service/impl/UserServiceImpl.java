package com.finalproject.mvpass.service.impl;

import com.finalproject.mvpass.entity.*;
import com.finalproject.mvpass.model.LoginModal;
import com.finalproject.mvpass.model.UserModel;
import com.finalproject.mvpass.repository.LicenseDatasRepository;
import com.finalproject.mvpass.repository.PasswordResetTokenRepository;
import com.finalproject.mvpass.repository.UserRepository;
import com.finalproject.mvpass.repository.VerificationTokenRepository;
import com.finalproject.mvpass.common.ErrorHandle;
import com.finalproject.mvpass.response.LoginResponse;
import com.finalproject.mvpass.service.UserService;
import com.finalproject.mvpass.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LicenseDatasRepository licenseDatasRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public User registerUser(UserModel userModel) {
        LicenseData licenseData1 = licenseDatasRepository.findByLicenseNo(userModel.getLicenceno());

        if (licenseData1 != null) {
            if (userRepository.existsByEmail(userModel.getEmail())) {
                throw new ErrorHandle("Email is already registered.");
            }
            if (userRepository.existsByMobile(userModel.getMobile())) {
                throw new ErrorHandle("Mobile number is already registered.");
            }

            User user = new User(
                    userModel.getUserfname(),
                    userModel.getUserlname(),
                    userModel.getAddress(),
                    userModel.getNic(),
                    userModel.getLicenceno(),
                    userModel.getEmail(),
                    userModel.getGender(),
                    userModel.getMobile(),
                    this.passwordEncoder.encode(userModel.getPassword())
            );

            userRepository.save(user);
            return user;
        } else {
            throw new ErrorHandle("Your license number is not eligible for registration.");
        }
    }




    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken
                = new VerificationToken(user, token);

        verificationTokenRepository.save(verificationToken);
    }

//    @Override
//    public String validateVerificationToken(String token) {
//        VerificationToken verificationToken
//                = verificationTokenRepository.findByToken(token);
//
//        if (verificationToken == null) {
//            return "invalid";
//        }
//
//        User user = verificationToken.getUser();
//        Calendar cal = Calendar.getInstance();
//
//        if ((verificationToken.getExpirationTime().getTime()
//                - cal.getTime().getTime()) <= 0) {
//            verificationTokenRepository.delete(verificationToken);
//            return "expired";
//        }
//
//        user.setEnabled(true);
//        userRepository.save(user);
//        return "valid";
//    }


    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            return "invalid";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        Date expirationTime = Date.from(verificationToken.getExpirationTime());

        // Log relevant information for debugging
        log.info("Current time: {}", cal.getTime());
        log.info("Token expiration time: {}", expirationTime);

        if (expirationTime.before(cal.getTime())) {
            // Token has expired
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }

        // Token is still valid
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }


    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken
                = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public User findUserByNic(String nic) {
        return userRepository.findByNic(nic);
    }

    @Override
    public void createPasswordTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken =new PasswordResetToken(user,token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

//    @Override
//    public String validatePasswordRestToken(String token) {
//        PasswordResetToken passwordResetToken
//                = passwordResetTokenRepository.findByToken(token);
//
//        if (passwordResetToken == null) {
//            return "invalid";
//        }
//
//        User user = passwordResetToken.getUser();
//        Calendar cal = Calendar.getInstance();
//
//        if ((passwordResetToken.getExpirationTime().getTime()
//                - cal.getTime().getTime()) <= 0) {
//            passwordResetTokenRepository.delete(passwordResetToken);
//            return "expired";
//        }
//        return "valid";
//    }
    @Override
    public String validatePasswordRestToken(String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);

        if (passwordResetToken == null) {
            return "invalid";
        }

        User user = passwordResetToken.getUser();
        Calendar cal = Calendar.getInstance();
        Date expirationTime = Date.from(passwordResetToken.getExpirationTime());

        // Log relevant information for debugging
        log.info("Current time: {}", cal.getTime());
        log.info("Token expiration time: {}", expirationTime);

        if (expirationTime.before(cal.getTime())) {
            // Token has expired
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }

        // Token is still valid
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }


    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword,user.getPassword());
    }

    @Override
    public LoginResponse LoginUser(LoginModal loginModal) { try {
        String msg = "";
        User user1 = userRepository.findByNic(loginModal.getNic());
        if (user1 != null) {
            String password = loginModal.getPassword();
            String encodedPassword = user1.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                User user = userRepository.findOneByNicAndPassword(loginModal.getNic(), encodedPassword);
                if (user.isEnabled()) {
                    Admin admin = new Admin();
                    String token = jwtUtils.generateJwt(user, admin);
//                    String token = UUID.randomUUID().toString();

                    return new LoginResponse("Login Succeed", true , token);
                } else {
                    return new LoginResponse("Login Faild", false , "");
                }
            } else {
                return new LoginResponse("Password Not Match", false , "");
            }
        } else {
            return new LoginResponse("NIC Not Exits", false , "");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new LoginResponse("An error occurred during login", false, "")).getBody();
    }
    }
}

