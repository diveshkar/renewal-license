package com.finalproject.mvpass.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class LoginResponse {
        String message;
        Boolean Status;
        String token;
    }


