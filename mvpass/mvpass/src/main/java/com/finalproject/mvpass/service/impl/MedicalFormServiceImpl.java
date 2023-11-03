package com.finalproject.mvpass.service.impl;

import com.finalproject.mvpass.entity.MedicalForm;
import com.finalproject.mvpass.model.MedicalFormModal;
import com.finalproject.mvpass.repository.MedicalFormRepository;
import com.finalproject.mvpass.service.MedicalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalFormServiceImpl implements MedicalFormService {

    @Autowired
    private MedicalFormRepository medicalFormRepository;
    @Override
    public MedicalForm saveMedicalData(MedicalFormModal medicalFormModal) {
        MedicalForm medicalForm = new MedicalForm(
                medicalFormModal.getFullname(),
                medicalFormModal.getBookId(),
                medicalFormModal.getExaminationDate(),
                medicalFormModal.getPhoneNumber(),
                medicalFormModal.getAddress(),
                medicalFormModal.getNicNumber(),
                medicalFormModal.getDob(),
                medicalFormModal.getAge(),
                medicalFormModal.getSex(),
                medicalFormModal.getBmi(),
                medicalFormModal.getWeight(),
                medicalFormModal.getLims(),
                medicalFormModal.getVision(),
                medicalFormModal.getHearing(),
                medicalFormModal.getPulse(),
                medicalFormModal.getBloodpressure(),
                medicalFormModal.getXray(),
                medicalFormModal.getBlood(),
                medicalFormModal.getRbs(),
                medicalFormModal.getSkelDefuse(),
                medicalFormModal.getProblemInVision(),
                medicalFormModal.getHeartMurmus(),
                medicalFormModal.getLungDisease(),
                medicalFormModal.getPsychologicalStatus(),
                medicalFormModal.getCentralNervousSystem(),
                medicalFormModal.getMedicalFromApproved(),
                medicalFormModal.getText(),
                medicalFormModal.getVehicles(),
                medicalFormModal.getSpecial(),
                medicalFormModal.getHeight()
        );
        medicalFormRepository.save(medicalForm);
        return medicalForm;
    }
}
