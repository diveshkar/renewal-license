//package com.finalproject.mvpass.event.listener;
//
//import com.finalproject.mvpass.entity.User;
//import com.finalproject.mvpass.event.RegistrationCompleteEvent;
//import com.finalproject.mvpass.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//@Slf4j
//public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void onApplicationEvent(RegistrationCompleteEvent event) {
//        //Create the Verification Token for the User with Link
//        User user = event.getUser();
//        String token = UUID.randomUUID().toString();
//        userService.saveVerificationTokenForUser(token,user);
//        //Send Mail to user
//        String url =
//                event.getApplicationUrl()
//                        + "/verifyRegistration?token="
//                        + token;
//
//        //sendVerificationEmail()
//        log.info("Click the link to verify your account: {}",
//                url);
//    }
//}

package com.finalproject.mvpass.event.listener;

import com.finalproject.mvpass.entity.User;
import com.finalproject.mvpass.event.RegistrationCompleteEvent;
import com.finalproject.mvpass.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Create the Verification Token for the User with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);

        // Send Mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;

        // Send verification email
        sendVerificationEmail(user.getEmail(), url);

        log.info("Verification link sent to user email: {}", user.getEmail());
    }

    private void sendVerificationEmail(String toEmail, String verificationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Verify Your Account");
        message.setText("Click the link to verify your account: " + verificationLink);

        mailSender.send(message);
    }
}
