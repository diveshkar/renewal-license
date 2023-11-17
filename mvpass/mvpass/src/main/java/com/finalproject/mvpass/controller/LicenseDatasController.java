package com.finalproject.mvpass.controller;

import com.finalproject.mvpass.entity.LicenseData;
import com.finalproject.mvpass.model.LicenseNewUserDataModal;
import com.finalproject.mvpass.response.LicenseDatasReponse;
import com.finalproject.mvpass.service.LicenseDatasService;
import com.finalproject.mvpass.util.JwtUtils;
import org.apache.tomcat.util.http.parser.Authorization;
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

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/newLicenseUser")
    public String newLicenseUser(@RequestBody LicenseNewUserDataModal licenseNewUserDataModal, @RequestHeader(value = "Authorization") String auth){
        LicenseData licenseData = licenseDatasService.newLicenseUser(licenseNewUserDataModal);
        return "New License User Added";
    }
    @GetMapping("/licenseData")
    public ResponseEntity<LicenseDatasReponse> sendLicenseData(@RequestHeader(value = "Authorization", defaultValue = "") String auth) throws Exception{
//        jwtUtils.verify(auth);
//        System.out.println(auth);
        LicenseDatasReponse licenseDatasReponse = licenseDatasService.sendLicenseData();
        return new ResponseEntity<>(licenseDatasReponse,HttpStatusCode.valueOf(200));
    };
};



















