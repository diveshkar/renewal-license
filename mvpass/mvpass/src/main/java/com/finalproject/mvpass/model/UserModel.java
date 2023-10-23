package com.finalproject.mvpass.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class UserModel {
        private String userfname;
        private String userlname;
        private String address;
        private String nic;
        private String licenceno;
        private String email;
        private String gender;
        private int mobile;
        private String password;


    }

