package org.sectorsalud.sectorsalud.api.service;
import java.util.UUID;
import org.sectorsalud.sectorsalud.api.entity.Document;
import java.util.List;
import org.sectorsalud.sectorsalud.api.dto.DocumentResponseDTO;
import org.sectorsalud.sectorsalud.api.dto.CreateDocumentDTO;
public interface DocumentService {

    List<DocumentResponseDTO> getByCedula(String cedula);
    void validateOwnership(UUID documentId, String cedula);
    void create(CreateDocumentDTO dto);
}