package com.finalproject.mvpass.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenseNewUserDataModal {
    private String licenseNo;
    private String licenseType;
    private String nic;
    private String name;
    private String photo;
    private String address;
    private Date dob;
    private String bloodGroup;
    private Date dateOfIssue;
    private Date dateOfExpiry;
}
