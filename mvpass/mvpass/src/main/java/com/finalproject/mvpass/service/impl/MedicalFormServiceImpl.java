package com.finalproject.mvpass.service.impl;

import com.finalproject.mvpass.entity.MedicalForm;
import com.finalproject.mvpass.model.MedicalFormModal;
import com.finalproject.mvpass.repository.MedicalFormRepository;
import com.finalproject.mvpass.service.MedicalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class MedicalFormServiceImpl implements MedicalFormService {

    private static final String FILE_STORAGE_DIRECTORY = "src/main/resources/static/images";

    @Autowired
    private MedicalFormRepository medicalFormRepository;

    @Override
    public MedicalForm saveMedicalData(MedicalFormModal medicalFormModal, MultipartFile imageFile) {
        try {

            createFileStorageDirectory();
            String filename = medicalFormModal.getFullname() + "_" + imageFile.getOriginalFilename();
            System.out.println(medicalFormModal.getNicNumber());
            saveImageToFileStorage(imageFile, filename);
            MedicalForm medicalForm = new MedicalForm(
                    medicalFormModal.getFullname(),
                    medicalFormModal.getBookId(),
                    medicalFormModal.getExaminationDate(),
                    medicalFormModal.getPhoneNumber(),
                    medicalFormModal.getAddress(),
                    medicalFormModal.getNicNumber(),
                    medicalFormModal.getDob(),
                    medicalFormModal.getAge(),
                    medicalFormModal.getSex(),
                    medicalFormModal.getBmi(),
                    medicalFormModal.getWeight(),
                    medicalFormModal.getLims(),
                    medicalFormModal.getVision(),
                    medicalFormModal.getHearing(),
                    medicalFormModal.getPulse(),
                    medicalFormModal.getBloodpressure(),
                    medicalFormModal.getXray(),
                    medicalFormModal.getBlood(),
                    medicalFormModal.getRbs(),
                    medicalFormModal.getSkelDefuse(),
                    medicalFormModal.getProblemInVision(),
                    medicalFormModal.getHeartMurmus(),
                    medicalFormModal.getLungDisease(),
                    medicalFormModal.getPsychologicalStatus(),
                    medicalFormModal.getCentralNervousSystem(),
                    medicalFormModal.getMedicalFromApproved(),
                    medicalFormModal.getText(),
                    medicalFormModal.getVehicles(),
                    medicalFormModal.getSpecial(),
                    medicalFormModal.getHeight(),
                    filename
            );
            medicalFormRepository.save(medicalForm);

            return medicalForm;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save the image: " + e.getMessage(), e);
        }
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
