package org.sectorsalud.sectorsalud.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.sectorsalud.sectorsalud.api.dto.CreateDocumentDTO;
import java.util.List;
import java.util.UUID;
import org.sectorsalud.sectorsalud.api.service.DocumentService;
import org.sectorsalud.sectorsalud.api.entity.Document;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.sectorsalud.sectorsalud.api.dto.DocumentResponseDTO;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public List<DocumentResponseDTO> getDocuments(Authentication authentication) {

        String cedula = authentication.getName();

        return documentService.getByCedula(cedula);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<?> download(@PathVariable UUID id,
                                      Authentication authentication) {

        String cedula = authentication.getName();

        documentService.validateOwnership(id, cedula);

        String signedUrl = "https://guardarpdf.com/documents/"
                + id
                + "?token="
                + UUID.randomUUID()
                + "&expires=10min";

        return ResponseEntity.ok(
                Map.of(
                        "message", "Descarga autorizada",
                        "url", signedUrl,
                        "expiresIn", "10 minutos"
                )
        );
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateDocumentDTO dto) {

        documentService.create(dto);

        return ResponseEntity.ok("Documento creado correctamente");
    }
}