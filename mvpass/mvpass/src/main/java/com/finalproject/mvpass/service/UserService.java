package com.finalproject.mvpass.service;

import com.finalproject.mvpass.common.APIResponse;
import com.finalproject.mvpass.entity.User;
import com.finalproject.mvpass.entity.VerificationToken;
import com.finalproject.mvpass.model.LoginModal;
import com.finalproject.mvpass.model.UserModel;
import com.finalproject.mvpass.response.LoginResponse;

import java.util.Optional;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByNic(String nic);

    void createPasswordTokenForUser(User user, String token);

    String validatePasswordRestToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    LoginResponse LoginUser(LoginModal loginModal);

    APIResponse mediApprove();
    APIResponse licenseView();
}
