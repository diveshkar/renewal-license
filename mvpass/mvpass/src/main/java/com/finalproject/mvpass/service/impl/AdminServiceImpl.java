package com.finalproject.mvpass.service.impl;

import com.finalproject.mvpass.entity.Admin;
import com.finalproject.mvpass.entity.User;
import com.finalproject.mvpass.model.AdminLoginModel;
import com.finalproject.mvpass.repository.AdminRepository;
import com.finalproject.mvpass.response.AdminResponse;
import com.finalproject.mvpass.response.LoginResponse;
import com.finalproject.mvpass.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Override
    public AdminResponse AdminLogin(AdminLoginModel adminLoginModel) {
        try {
            String msg = "";
            Admin admin1 = adminRepository.findByAdminname(adminLoginModel.getAdminname());
            if (admin1 != null) {
                String password = adminLoginModel.getPassword();
                String storedPassword = admin1.getAdminpassword();
                String Role = adminLoginModel.getRole();
                String StoredRole = admin1.getRole();
//                boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
                if (password.equals(storedPassword) && Role.equalsIgnoreCase(StoredRole)) {
                    Optional<Admin> admin = adminRepository.findOneByAdminnameAndAdminpassword(adminLoginModel.getAdminname(), storedPassword);
                    if (admin.isPresent()) {
                        String MedicalAdminToken = UUID.randomUUID().toString();
                        String RenewalAdminToken = UUID.randomUUID().toString();
                        return new AdminResponse("Login Succeed", true , MedicalAdminToken , RenewalAdminToken, StoredRole);
                    } else {
                        return new AdminResponse("Login Faild", false , "","" , "");
                    }
                } else {
                    return new AdminResponse("Password/UserRole Not Match", false , "" , "", "");
                }
            } else {
                return new AdminResponse("AdminName Not Exits", false , "","", "");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AdminResponse("An error occurred during login", false, "","","")).getBody();
        }
    }
}
