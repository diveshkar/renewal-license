package com.finalproject.mvpass.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginModel {
    private String adminname;
    private String password;
    private String role;


}
