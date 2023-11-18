package com.finalproject.mvpass.controller;

import com.finalproject.mvpass.entity.MedicalForm;
import com.finalproject.mvpass.model.MedicalFormModal;
import com.finalproject.mvpass.response.MedicalFormResponse;
import com.finalproject.mvpass.service.MedicalFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("api/v1/admin/medical")
@Slf4j
public class MedicalFormController {


    @Autowired
    private MedicalFormService medicalFormService;


    @PostMapping("/medicaldata")
    public ResponseEntity<MedicalFormResponse> saveMedicalData(@ModelAttribute MedicalFormModal medicalFormModal, @RequestHeader(value = "Authorization", defaultValue = "") String auth) throws IOException {
        MultipartFile imageFile = medicalFormModal.getImage();
//        MultipartFile textFile = (MultipartFile) medicalFormModal;,
//        Date hi = new Date();
        MedicalForm medicalForm = medicalFormService.saveMedicalData(medicalFormModal, imageFile);
        String imageUrl =  medicalForm.getLicenseImgUrl();
        Resource file = new ClassPathResource("static/images/" + imageUrl);
//        byte[] imagelicense = Files.readAllBytes(Path.of(file.getURI()));
        String contentType;
        contentType = URLConnection.guessContentTypeFromName(file.getFilename());
        MedicalFormResponse medicalFormResponse = new MedicalFormResponse();
        medicalFormResponse.setMessage("Success");
        medicalFormResponse.setImageUrl("http://localhost:8080/static/images/" + imageUrl);
        medicalFormResponse.setContentType(contentType);
        medicalFormResponse.setImageName(imageUrl);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(medicalFormResponse);
    }
}
