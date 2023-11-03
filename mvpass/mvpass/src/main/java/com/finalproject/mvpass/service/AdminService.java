package com.finalproject.mvpass.service;

import com.finalproject.mvpass.entity.MedicalForm;
import com.finalproject.mvpass.model.AdminLoginModel;
import com.finalproject.mvpass.model.MedicalFormModal;
import com.finalproject.mvpass.response.AdminResponse;

public interface AdminService {
    AdminResponse AdminLogin(AdminLoginModel adminLoginModel);


}
