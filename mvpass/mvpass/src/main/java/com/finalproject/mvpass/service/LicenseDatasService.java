package com.finalproject.mvpass.service;

import com.finalproject.mvpass.entity.LicenseData;
import com.finalproject.mvpass.model.LicenseNewUserDataModal;
import com.finalproject.mvpass.response.LicenseDatasReponse;

public interface LicenseDatasService {
    LicenseDatasReponse sendLicenseData();

    LicenseData newLicenseUser(LicenseNewUserDataModal licenseNewUserDataModal);
}
