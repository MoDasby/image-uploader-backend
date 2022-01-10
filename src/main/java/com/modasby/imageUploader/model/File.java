package com.modasby.imageUploader.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class File {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    private String name;
    private String contentType;
    private Long size;

    @Lob
    private byte[] data;
}
