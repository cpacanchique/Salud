package org.sectorsalud.sectorsalud.api.controller;

import org.springframework.web.bind.annotation.*;
import org.sectorsalud.sectorsalud.api.entity.DocumentType;
import org.sectorsalud.sectorsalud.api.service.DocumentTypeService;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/document-types")
public class DocumentTypeController {

    private final DocumentTypeService service;

    public DocumentTypeController(DocumentTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<DocumentType> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<?> createType(@RequestBody DocumentType request) {

        service.createType(request);

        return ResponseEntity.ok("Tipo de documento creado correctamente");
    }
}