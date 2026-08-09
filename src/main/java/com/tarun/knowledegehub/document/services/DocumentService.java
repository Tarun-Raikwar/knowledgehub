package com.tarun.knowledegehub.document.services;

import com.tarun.knowledegehub.document.entity.fileMetadata;
import com.tarun.knowledegehub.document.repository.FileMetadataRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class DocumentService {

    private final FileMetadataRepository fileMetadataRepository;
    private final Path uploadStorageLocation;

    public DocumentService(FileMetadataRepository fileMetadataRepository,
                           @Value("${file.upload-dir:uploads}") String uploadDir) {
        this.fileMetadataRepository = fileMetadataRepository;
        this.uploadStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(this.uploadStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload storage directory: " + this.uploadStorageLocation, e);
        }
    }

    public fileMetadata storeFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Cannot store empty file.");
        }

        String originalFilename = StringUtils.cleanPath(
                file.getOriginalFilename() != null ? file.getOriginalFilename() : "file_" + System.currentTimeMillis()
        );

        if (originalFilename.contains("..")) {
            throw new IllegalArgumentException("Invalid file path sequence in filename: " + originalFilename);
        }

        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFilename;

        try {
            Path targetLocation = this.uploadStorageLocation.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            fileMetadata metadata = fileMetadata.builder()
                    .fileName(originalFilename)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .filePath(targetLocation.toAbsolutePath().toString())
                    .build();

            return fileMetadataRepository.save(metadata);

        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file " + originalFilename, ex);
        }
    }
}
