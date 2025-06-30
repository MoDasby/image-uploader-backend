package com.modasby.imageUploader.repository;

import com.modasby.imageUploader.entity.ImageMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageMetadataRepository extends JpaRepository<ImageMetadata, String> {
    
}