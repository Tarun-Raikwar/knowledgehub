package com.tarun.knowledegehub.document.services;

import com.tarun.knowledegehub.document.entity.DocumentChunk;
import com.tarun.knowledegehub.document.entity.FileMetadata;
import com.tarun.knowledegehub.document.repository.DocumentChunkRepository;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

@Service
public class VectorStoreService {

    private final EmbeddingModel embeddingModel;
    private final DocumentChunkRepository documentChunkRepository;

    public VectorStoreService(
            EmbeddingModel embeddingModel,
            DocumentChunkRepository documentChunkRepository) {

        this.embeddingModel = embeddingModel;
        this.documentChunkRepository = documentChunkRepository;
    }

    public void storeChunk(FileMetadata fileMetadata, String chunkText) {
        if (chunkText != null) {
            chunkText = chunkText.replace("\u0000", "");
        }


        float[] embedding =
                embeddingModel.embed(chunkText);

        DocumentChunk chunk = new DocumentChunk();

        chunk.setFileMetadata(fileMetadata);
        chunk.setChunkText(chunkText);
        chunk.setEmbedding(embedding);

        documentChunkRepository.save(chunk);
    }
}

