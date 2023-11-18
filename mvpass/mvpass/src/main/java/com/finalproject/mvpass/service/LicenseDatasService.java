package com.finalproject.mvpass.service;

import com.finalproject.mvpass.entity.LicenseData;
import com.finalproject.mvpass.model.LicenseNewUserDataModal;
import com.finalproject.mvpass.response.LicenseDatasReponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LicenseDatasService {
    LicenseDatasReponse sendLicenseData();

//    LicenseData newLicenseUser(LicenseNewUserDataModal licenseNewUserDataModal, MultipartFile LicenseImg MultipartFile imageFile);

    LicenseData newLicenseUser(LicenseNewUserDataModal licenseNewUserDataModal, MultipartFile imageFile) throws IOException;
}
