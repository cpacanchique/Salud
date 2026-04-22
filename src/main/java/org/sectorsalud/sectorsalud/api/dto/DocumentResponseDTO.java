package org.sectorsalud.sectorsalud.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import com.fasterxml.jackson.databind.JsonNode;
public class DocumentResponseDTO {

    private UUID id;
    private String type;
    private String status;
    private JsonNode metadata;
    private LocalDateTime createdAt;

    public DocumentResponseDTO(UUID id, String type, String status, JsonNode metadata, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.metadata = metadata;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public JsonNode getMetadata() { return metadata; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}