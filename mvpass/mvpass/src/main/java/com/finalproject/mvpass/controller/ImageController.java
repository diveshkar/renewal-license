package com.finalproject.mvpass.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {

    private static final String FILE_STORAGE_DIRECTORY = "src/main/resources/static/NewLicenseUser"; // Adjust this based on your actual file storage directory

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
}
