package com.finalproject.mvpass.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalFormResponse {
    private String imageName;
    private String imageUrl;
    private String contentType;
    private String message;

}
