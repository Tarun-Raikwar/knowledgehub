package com.tarun.knowledegehub.document.repository;

import com.tarun.knowledegehub.document.entity.DocumentChunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentChunkRepository
        extends JpaRepository<DocumentChunk, Long> {
}
