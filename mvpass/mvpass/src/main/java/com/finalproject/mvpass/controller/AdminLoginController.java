package com.finalproject.mvpass.controller;

import com.finalproject.mvpass.entity.MedicalForm;
import com.finalproject.mvpass.model.AdminLoginModel;
import com.finalproject.mvpass.model.MedicalFormModal;
import com.finalproject.mvpass.response.AdminResponse;
import com.finalproject.mvpass.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/admin")
@Slf4j
public class AdminLoginController {


    @Autowired
    private AdminService adminService;
    @PostMapping("/login/{adminname}")
    public ResponseEntity<AdminResponse> AdminLogin(@RequestBody AdminLoginModel adminLoginModel)
    {
        AdminResponse adminResponse = adminService.AdminLogin(adminLoginModel);
        return ResponseEntity.ok(adminResponse);
    }

}
