package com.finalproject.mvpass.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {
    private String Message;
    private Boolean Status;
    private String token;
    private String Role;
}
