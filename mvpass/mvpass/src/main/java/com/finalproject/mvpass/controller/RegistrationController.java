package com.finalproject.mvpass.controller;


import com.finalproject.mvpass.entity.User;
import com.finalproject.mvpass.entity.VerificationToken;
import com.finalproject.mvpass.event.RegistrationCompleteEvent;
import com.finalproject.mvpass.model.LoginModal;
import com.finalproject.mvpass.model.PasswordModel;
import com.finalproject.mvpass.model.UserModel;
import com.finalproject.mvpass.response.ErrorHandle;
import com.finalproject.mvpass.response.LoginResponse;
import com.finalproject.mvpass.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        try {
            User user = userService.registerUser(userModel);
            publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
                return "Success, For Verify Click the link inside your Email" + " " + userModel.getEmail();
    } catch (Exception exception){
                return "Error:" + " " + exception.getMessage();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody LoginModal loginModal)
    {
        LoginResponse loginResponse = userService.LoginUser(loginModal);
        return ResponseEntity.ok(loginResponse);
    }


    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")) {
            return "User Verified Successfully";
        }
        return "Bad User";
    }


    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest request) {
        VerificationToken verificationToken
                = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken, user.getEmail());
        return "Verification Link Sent";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
       User user = userService.findUserByNic(passwordModel.getNic());
       String url = "";
       if(user!=null){
           String token = UUID.randomUUID().toString();
           userService.createPasswordTokenForUser(user,token);
           url = passwordResetTokenMail(user,applicationUrl(request),token);
       }
       return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token,@RequestBody PasswordModel passwordModel){
        String result = userService.validatePasswordRestToken(token);
        if(!result.equalsIgnoreCase("valid")){
            return "Invalid Token";
        }
        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if(user.isPresent()){
            userService.changePassword(user.get(),passwordModel.getNewPassword());
            return "Password Rest Successfully";
        }else {
            return "Invalid Token";
        }
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url =
                applicationUrl
                        + "/savePassword?token="
                        + token;

        //sendresetpassword()
        log.info("Click the link to Reset Password: {}",
                url);
        return url;
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByNic(passwordModel.getNic());
        if(!userService.checkIfValidOldPassword(user,passwordModel.getOldPassword())){
            return "Invalid Old Password";
        }else {
            //Save New Password
            userService.changePassword(user,passwordModel.getNewPassword());
            return "Password Change Successfully";
        }


    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken,String toEmail) {
        String url =
                applicationUrl
                        + "/verifyRegistration?token="
                        + verificationToken.getToken();

        //sendVerificationEmail()
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("ResendVerificationLink");
        message.setText("Click the link to verify your account: " +
                url);
        mailSender.send(message);
//        log.info("Click the link to verify your account: {}",
//                url);
    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                "/api/v1/user"+
                request.getContextPath();
    }
}