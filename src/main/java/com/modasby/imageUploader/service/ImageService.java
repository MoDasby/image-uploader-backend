package com.modasby.imageUploader.service;

import com.modasby.imageUploader.entity.ImageMetadata;
import com.modasby.imageUploader.exception.ResourceNotFoundException;
import com.modasby.imageUploader.repository.ImageMetadataRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final S3Service s3Service;
    private final ImageMetadataRepository imageMetadataRepository;

    public ImageService(S3Service s3Service, ImageMetadataRepository imageMetadataRepository) {
        this.s3Service = s3Service;
        this.imageMetadataRepository = imageMetadataRepository;
    }

    public String getImageUrl(String imgId) {
        ImageMetadata imageMetadata = imageMetadataRepository.findById(imgId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + imgId));

        return s3Service.getPresignedUrl(imageMetadata.getName());
    }
}
