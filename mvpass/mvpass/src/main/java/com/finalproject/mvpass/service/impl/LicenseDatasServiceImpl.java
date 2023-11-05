package com.finalproject.mvpass.service.impl;

import com.finalproject.mvpass.entity.Admin;
import com.finalproject.mvpass.entity.LicenseData;
import com.finalproject.mvpass.model.LicenseNewUserDataModal;
import com.finalproject.mvpass.repository.LicenseDatasRepository;
import com.finalproject.mvpass.response.AdminResponse;
import com.finalproject.mvpass.response.ErrorHandle;
import com.finalproject.mvpass.response.LicenseDatasReponse;
import com.finalproject.mvpass.service.LicenseDatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenseDatasServiceImpl implements LicenseDatasService {

    @Autowired
    private LicenseDatasRepository licenseDatasRepository;

    @Override
    public LicenseDatasReponse sendLicenseData() {
        LicenseDatasReponse licenseDatasReponse = new LicenseDatasReponse();
        try {
            List<LicenseData> licenseDataList = licenseDatasRepository.findAll();
            licenseDatasReponse.setLicenseDataList(licenseDataList);
        } catch (Exception e) {
            licenseDatasReponse.setMessage("An error occurred while fetching license data.");
        }
        return licenseDatasReponse;
    }

    @Override
    public LicenseData newLicenseUser(LicenseNewUserDataModal licenseNewUserDataModal) {
        LicenseData existingLicense = licenseDatasRepository.findByLicenseNo(licenseNewUserDataModal.getLicenseNo());

        if (existingLicense != null) {
            throw new ErrorHandle("License number already exists.");
        }
        LicenseData newLicenseData = new LicenseData(
                licenseNewUserDataModal.getLicenseNo(),
                licenseNewUserDataModal.getLicenseType(),
                licenseNewUserDataModal.getNic(),
                licenseNewUserDataModal.getName(),
                licenseNewUserDataModal.getPhoto(),
                licenseNewUserDataModal.getAddress(),
                licenseNewUserDataModal.getDob(),
                licenseNewUserDataModal.getBloodGroup(),
                licenseNewUserDataModal.getDateOfIssue(),
                licenseNewUserDataModal.getDateOfExpiry()
        );

        return licenseDatasRepository.save(newLicenseData);
    }

}

