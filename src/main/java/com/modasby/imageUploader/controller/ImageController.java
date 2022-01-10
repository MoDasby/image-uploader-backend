package com.modasby.imageUploader.controller;

import com.modasby.imageUploader.model.File;
import com.modasby.imageUploader.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    FileStorageService fileStorageService;

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") String id, HttpServletRequest request) {
        File file = fileStorageService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.getName() + "\"")
                .body(file.getData());
    }

    @PostMapping("/image/upload")
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(fileStorageService.saveFile(file));
    }
}
