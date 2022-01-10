package com.modasby.imageUploader.service;

import com.modasby.imageUploader.exception.CannotSaveFileException;
import com.modasby.imageUploader.model.File;
import com.modasby.imageUploader.model.FileInfo;
import com.modasby.imageUploader.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@Service
public class FileStorageService {

    @Autowired
    FileRepository fileRepository;

    public FileInfo saveFile(MultipartFile file) {
        try {
            File entity = new File();

            if (Objects.isNull(file.getOriginalFilename())) {
                throw new CannotSaveFileException("Could not save file. File name is null.");
            }

            entity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            entity.setContentType(file.getContentType());
            entity.setSize(file.getSize());
            entity.setData(file.getBytes());

            File savedFile = fileRepository.save(entity);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/image/")
                    .path(savedFile.getId())
                    .toUriString();

            return new FileInfo(
                    savedFile.getId(),
                    savedFile.getName(),
                    savedFile.getContentType(),
                    fileDownloadUri
            );
        } catch (Exception e) {
            throw new CannotSaveFileException("could not save file, try again");
        }
    }

    public File getFile(String id) {

        return fileRepository.findById(id).orElseThrow(() -> new CannotSaveFileException("File not found"));
    }
}
