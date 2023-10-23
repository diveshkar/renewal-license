package com.finalproject.mvpass.model;

import lombok.Data;

@Data
public class PasswordModel {
    private String nic;
    private String oldPassword;
    private String newPassword;
}
