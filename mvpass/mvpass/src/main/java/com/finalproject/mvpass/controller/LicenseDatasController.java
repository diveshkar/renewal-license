package com.finalproject.mvpass.controller;

import com.finalproject.mvpass.entity.LicenseData;
import com.finalproject.mvpass.model.LicenseNewUserDataModal;
import com.finalproject.mvpass.response.LicenseDatasReponse;
import com.finalproject.mvpass.service.LicenseDatasService;
import com.finalproject.mvpass.util.JwtUtils;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin
@RequestMapping("api/v1/admin/renewal")
public class LicenseDatasController {

    @Autowired
    private LicenseDatasService licenseDatasService;

    @Autowired
    private JwtUtils jwtUtils;

    private static final String FILE_STORAGE_DIRECTORY = "src/main/resources/static/NewLicenseUser";

    @PostMapping("/newLicenseUser")
    public String newLicenseUser(@ModelAttribute LicenseNewUserDataModal licenseNewUserDataModal, @RequestHeader(value = "Authorization") String auth) throws IOException {
        MultipartFile imageFile = licenseNewUserDataModal.getPhoto();
        LicenseData licenseData = licenseDatasService.newLicenseUser(licenseNewUserDataModal, imageFile);
        return "New License User Added";
    }
    @GetMapping("/licenseData")
    public ResponseEntity<LicenseDatasReponse> sendLicenseData(@RequestHeader(value = "Authorization", defaultValue = "") String auth) throws Exception{
//        jwtUtils.verify(auth);
//        System.out.println(auth);
        LicenseDatasReponse licenseDatasReponse = licenseDatasService.sendLicenseData();
        return new ResponseEntity<>(licenseDatasReponse,HttpStatusCode.valueOf(200));
    };

    @GetMapping("/getImage/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            ByteArrayResource resource = retryImageFromFileStorage(filename);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.IMAGE_JPEG) // Adjust the media type based on your image format
                    .body(resource);
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private ByteArrayResource retryImageFromFileStorage(String filename) throws IOException {
        Path filePath = Paths.get(FILE_STORAGE_DIRECTORY, filename);
        byte[] licenseImg = Files.readAllBytes(filePath);

        // Wrap the byte array in a ByteArrayResource
        return new ByteArrayResource(licenseImg);
    }


};



















