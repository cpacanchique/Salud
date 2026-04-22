package org.sectorsalud.sectorsalud.api.service;

import org.sectorsalud.sectorsalud.api.entity.DocumentType;

import java.util.List;

public interface DocumentTypeService {

    List<DocumentType> getAll();
    void createType(DocumentType type);
}