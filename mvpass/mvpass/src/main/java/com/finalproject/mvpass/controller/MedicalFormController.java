package com.finalproject.mvpass.controller;

import com.finalproject.mvpass.entity.MedicalForm;
import com.finalproject.mvpass.model.MedicalFormModal;
import com.finalproject.mvpass.service.MedicalFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/admin/medical")
@Slf4j
public class MedicalFormController {


    @Autowired
    private MedicalFormService medicalFormService;

    @PostMapping("/medicaldata")
    public ResponseEntity<String> saveMedicalData(@RequestBody MedicalFormModal medicalFormModal) {
        MedicalForm medicalForm = medicalFormService.saveMedicalData(medicalFormModal);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
