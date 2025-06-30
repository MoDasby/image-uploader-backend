package com.modasby.imageUploader.service;

import com.modasby.imageUploader.entity.ImageMetadata;
import com.modasby.imageUploader.exception.UnsupportedFileTypeException;
import com.modasby.imageUploader.repository.ImageMetadataRepository;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Service
public class UploadService {

    private final S3Service s3Service;
    private final ImageMetadataRepository imageMetadataRepository;
    private final Set<String> allowedContentTypes = Set.of(
            "image/png",
            "image/jpeg",
            "image/gif",
            "image/webp"
    );
    private final Tika tika = new Tika();

    public UploadService(
            S3Service s3Service,
            ImageMetadataRepository imageMetadataRepository
    ) {
        this.s3Service = s3Service;
        this.imageMetadataRepository = imageMetadataRepository;
    }

    public ImageMetadata uploadImage(MultipartFile file) {
        try (var inputStream = new BufferedInputStream(file.getInputStream())) {
            inputStream.mark(1024);
            String contentType = tika.detect(inputStream);
            inputStream.reset();

            if (!allowedContentTypes.contains(contentType)) {
                throw new UnsupportedFileTypeException("Unsupported file type: " + contentType);
            }

            String id = UUID.randomUUID().toString();
            String fileExt = contentType.split("/")[1];
            String fileName = id + "." + fileExt;

            long contentLength = file.getSize();

            s3Service.upload(inputStream, fileName, contentType, contentLength);

            ImageMetadata metadata = new ImageMetadata(
                    id,
                    fileName,
                    contentType,
                    contentLength,
                    Instant.now()
            );

            return imageMetadataRepository.save(metadata);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file input stream " + e, e);
        }
    }
}
