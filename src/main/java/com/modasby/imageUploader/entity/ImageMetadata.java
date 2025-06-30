package com.modasby.imageUploader.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class ImageMetadata {
    public ImageMetadata() {}

    public ImageMetadata(
            String id,
            String name,
            String contentType,
            Long size,
            Instant createdAt
    ) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.createdAt = createdAt;
    }

    @Id String id;
    String name;
    String contentType;
    Long size;
    Instant createdAt;

    public String getName() {
        return name;
    }
}
