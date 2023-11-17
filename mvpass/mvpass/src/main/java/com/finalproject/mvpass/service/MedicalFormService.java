package com.finalproject.mvpass.service;

import com.finalproject.mvpass.entity.MedicalForm;
import com.finalproject.mvpass.model.MedicalFormModal;
import org.springframework.web.multipart.MultipartFile;


public interface MedicalFormService {
//    MedicalForm saveMedicalData(MultipartFile textFile, MultipartFile imageFile);

    MedicalForm saveMedicalData(MedicalFormModal medicalFormModal, MultipartFile licenseImageUrl);
}
