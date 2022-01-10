package com.modasby.imageUploader.repository;

import com.modasby.imageUploader.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
}