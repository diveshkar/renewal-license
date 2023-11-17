package com.finalproject.mvpass.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalFormModal {

    private String fullname;
    private String bookId;
    private Date examinationDate;
    private Long phoneNumber;
    private String address;
    private String nicNumber;
    private Date dob;
    private Long age;
    private String sex;
    private String bmi;
    private String weight;
    private String lims;
    private String vision;
    private String hearing;
    private String pulse;
    private String bloodpressure;
    private String xray;
    private String blood;
    private String rbs;
    private String skelDefuse;
    private String problemInVision;
    private String heartMurmus;
    private String lungDisease;
    private String psychologicalStatus;
    private String centralNervousSystem;
    private Boolean medicalFromApproved;
    private String text;
    private String vehicles;
    private String special;
    private String height;
    private MultipartFile image;

}
