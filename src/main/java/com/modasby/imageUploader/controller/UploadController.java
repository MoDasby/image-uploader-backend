package com.modasby.imageUploader.controller;

import com.modasby.imageUploader.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(
            @RequestPart("file") MultipartFile file
    ) {
        return ResponseEntity.ok(this.uploadService.uploadImage(file));
    }
}
