package com.modasby.imageUploader.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor @Getter @Setter
public class FileInfo {
    private String id;
    private String name;
    private String contentType;
    private String url;

}
