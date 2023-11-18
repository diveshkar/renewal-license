package com.finalproject.mvpass.service.impl;

import com.finalproject.mvpass.entity.LicenseData;
import com.finalproject.mvpass.model.LicenseNewUserDataModal;
import com.finalproject.mvpass.repository.LicenseDatasRepository;
import com.finalproject.mvpass.common.ErrorHandle;
import com.finalproject.mvpass.response.LicenseDatasReponse;
import com.finalproject.mvpass.service.LicenseDatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class LicenseDatasServiceImpl implements LicenseDatasService {

    @Autowired
    private LicenseDatasRepository licenseDatasRepository;

    private static final String FILE_STORAGE_DIRECTORY = "src/main/resources/static/NewLicenseUser";

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
    public LicenseData newLicenseUser(LicenseNewUserDataModal licenseNewUserDataModal, MultipartFile imageFile) throws IOException {
        LicenseData existingLicense = licenseDatasRepository.findByLicenseNo(licenseNewUserDataModal.getLicenseNo());

        if (existingLicense != null) {
            throw new ErrorHandle("License number already exists.");
        }
        createFileStorageDirectory();
        String filename = licenseNewUserDataModal.getName() + "_" + imageFile.getOriginalFilename();
        saveImageToFileStorage(imageFile, filename);
        LicenseData newLicenseData = new LicenseData(
                licenseNewUserDataModal.getLicenseNo(),
                licenseNewUserDataModal.getLicenseType(),
                licenseNewUserDataModal.getNic(),
                licenseNewUserDataModal.getName(),
                filename,
                licenseNewUserDataModal.getAddress(),
                licenseNewUserDataModal.getDob(),
                licenseNewUserDataModal.getBloodGroup(),
                licenseNewUserDataModal.getDateOfIssue(),
                licenseNewUserDataModal.getDateOfExpiry()
        );

        return licenseDatasRepository.save(newLicenseData);
    }

    private void createFileStorageDirectory() throws IOException {
        Path directoryPath = Paths.get(FILE_STORAGE_DIRECTORY);
        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
    }

    private String generateUniqueFilename(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }

    private void saveImageToFileStorage(MultipartFile imageFile, String filename) throws IOException {
        byte[] imageBytes = imageFile.getBytes();
        Path filePath = Paths.get(FILE_STORAGE_DIRECTORY, filename);
        Files.write(filePath, imageBytes);
    }

}

