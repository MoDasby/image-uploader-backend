package com.modasby.imageUploader.controller;

import com.modasby.imageUploader.model.FileInfo;
import com.modasby.imageUploader.service.FileStorageService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    FileStorageService fileStorageService;

    @GetMapping("/image/download/{fileName:..+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.getFile(fileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception exception) {
            exception.printStackTrace();
            contentType = "image/*";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping(value = "/image/upload")
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = fileStorageService.saveFile(file);
            String fileUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/image/download/")
                    .path(fileName)
                    .toUriString();

            return ResponseEntity.ok(new FileInfo(fileName, fileUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cannot save file");
        }
    }
}
