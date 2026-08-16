package com.tarun.knowledegehub.document.controller;

import com.tarun.knowledegehub.document.entity.FileMetadata;
import com.tarun.knowledegehub.document.services.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class documentController {

    private final DocumentService documentService;

    public documentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/api/health")
    public String health() {
        return "KnowledgeHub AI is running!";
    }

    @PostMapping("/api/document/upload")
    public ResponseEntity<FileMetadata> uploadDocument(@RequestParam("file") MultipartFile file) {
        FileMetadata savedMetadata = documentService.storeFile(file);
        return ResponseEntity.ok(savedMetadata);
    }
}

