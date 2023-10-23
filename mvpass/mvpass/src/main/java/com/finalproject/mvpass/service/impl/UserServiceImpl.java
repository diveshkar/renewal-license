package com.finalproject.mvpass.service.impl;

import com.finalproject.mvpass.entity.PasswordResetToken;
import com.finalproject.mvpass.entity.User;
import com.finalproject.mvpass.entity.VerificationToken;
import com.finalproject.mvpass.model.LoginModal;
import com.finalproject.mvpass.model.UserModel;
import com.finalproject.mvpass.repository.PasswordResetTokenRepository;
import com.finalproject.mvpass.repository.UserRepository;
import com.finalproject.mvpass.repository.VerificationTokenRepository;
import com.finalproject.mvpass.response.LoginResponse;
import com.finalproject.mvpass.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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
    @Override
    public User registerUser(UserModel userModel) {
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
    public LoginResponse LoginUser(LoginModal loginModal) {
        try {
            String msg = "";
            User user1 = userRepository.findByNic(loginModal.getNic());
            if (user1 != null) {
                String password = loginModal.getPassword();
                String encodedPassword = user1.getPassword();
                boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
                if (isPwdRight) {
                    Optional<User> user = userRepository.findOneByNicAndPassword(loginModal.getNic(), encodedPassword);
                    if (user.isPresent()) {
                        String token = UUID.randomUUID().toString();
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

