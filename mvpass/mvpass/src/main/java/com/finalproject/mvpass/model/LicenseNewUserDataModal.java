package com.finalproject.mvpass.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenseNewUserDataModal {
    private String licenseNo;
    private String licenseType;
    private String nic;
    private String name;
    private MultipartFile photo;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String bloodGroup;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfIssue;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfExpiry;
}
