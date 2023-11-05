package com.finalproject.mvpass.controller;

import com.finalproject.mvpass.entity.LicenseData;
import com.finalproject.mvpass.model.LicenseNewUserDataModal;
import com.finalproject.mvpass.response.LicenseDatasReponse;
import com.finalproject.mvpass.service.LicenseDatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/admin/renewal")
public class LicenseDatasController {

    @Autowired
    private LicenseDatasService licenseDatasService;

    @PostMapping("/newLicenseUser")
    public String newLicenseUser(@RequestBody LicenseNewUserDataModal licenseNewUserDataModal){
        LicenseData licenseData = licenseDatasService.newLicenseUser(licenseNewUserDataModal);
        return "New License User Added";
    }
    @GetMapping("/licenseData")
    public ResponseEntity<LicenseDatasReponse> sendLicenseData(){
        LicenseDatasReponse licenseDatasReponse = licenseDatasService.sendLicenseData();
        return new ResponseEntity<>(licenseDatasReponse,HttpStatusCode.valueOf(200));
    };
};



















