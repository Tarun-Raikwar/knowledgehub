package com.tarun.knowledegehub.document.repository;

import com.tarun.knowledegehub.document.entity.fileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetadataRepository extends JpaRepository<fileMetadata, Long> {
}
