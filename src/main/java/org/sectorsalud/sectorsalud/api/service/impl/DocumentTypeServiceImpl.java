package org.sectorsalud.sectorsalud.api.service.impl;

import org.springframework.stereotype.Service;
import org.sectorsalud.sectorsalud.api.entity.DocumentType;
import org.sectorsalud.sectorsalud.api.repository.DocumentTypeRepository;
import org.sectorsalud.sectorsalud.api.service.DocumentTypeService;
import java.util.UUID;
import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository repository;

    public DocumentTypeServiceImpl(DocumentTypeRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<DocumentType> getAll() {
        return repository.findAll();
    }
    @Override
    public void createType(DocumentType type) {

        if (repository.existsByName(type.getName())) {
            throw new RuntimeException("El tipo de documento ya existe");
        }

        type.setId(UUID.randomUUID());

        repository.save(type);
    }



}