package com.modasby.imageUploader;

import com.modasby.imageUploader.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageUploaderApplication implements CommandLineRunner {

	@Autowired
	FileStorageService fileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(ImageUploaderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileStorageService.init();
	}
}
