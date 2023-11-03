package com.finalproject.mvpass.service;

import com.finalproject.mvpass.entity.MedicalForm;
import com.finalproject.mvpass.model.MedicalFormModal;



public interface MedicalFormService {
    MedicalForm saveMedicalData(MedicalFormModal medicalFormModal);
}
