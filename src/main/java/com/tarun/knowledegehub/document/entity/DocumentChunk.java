package com.tarun.knowledegehub.document.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "document_chunks")
@Getter()
@Setter()
@NoArgsConstructor()
@AllArgsConstructor()
@Builder()
public class DocumentChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "file_metadata_id")
    private FileMetadata fileMetadata;

    @Column(columnDefinition = "TEXT")
    private String chunkText;

    private float[] embedding;
}
