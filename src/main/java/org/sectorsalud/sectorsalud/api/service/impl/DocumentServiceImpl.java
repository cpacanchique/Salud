package org.sectorsalud.sectorsalud.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.sectorsalud.sectorsalud.api.service.DocumentService;
import org.sectorsalud.sectorsalud.api.repository.DocumentRepository;
import org.sectorsalud.sectorsalud.api.entity.Document;
import org.sectorsalud.sectorsalud.api.repository.PatientRepository;
import org.sectorsalud.sectorsalud.api.entity.Patient;
import java.util.List;
import java.util.UUID;
import org.sectorsalud.sectorsalud.api.dto.DocumentResponseDTO;
import org.sectorsalud.sectorsalud.api.repository.DocumentTypeRepository;
import org.sectorsalud.sectorsalud.api.dto.CreateDocumentDTO;
import org.sectorsalud.sectorsalud.api.entity.DocumentType;


@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final PatientRepository patientRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final ObjectMapper objectMapper;

    public DocumentServiceImpl(
            DocumentRepository documentRepository,
            PatientRepository patientRepository,
            DocumentTypeRepository documentTypeRepository,
            ObjectMapper objectMapper
    ) {
        this.documentRepository = documentRepository;
        this.patientRepository = patientRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<DocumentResponseDTO> getByCedula(String cedula) {

        Patient patient = patientRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        return documentRepository.findByPatient(patient)
                .stream()
                .map(doc -> new DocumentResponseDTO(
                        doc.getId(),
                        doc.getType(),
                        doc.getStatus(),
                        doc.getMetadata(),
                        doc.getCreatedAt()
                ))
                .toList();
    }

    @Override
    public void validateOwnership(UUID documentId, String cedula) {

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        if (!document.getPatient().getCedula().equals(cedula)) {
            throw new RuntimeException("No tienes acceso a este documento");
        }
    }

    @Override
    public void create(CreateDocumentDTO dto) {

        Patient patient = patientRepository.findByCedula(dto.getCedula())
                .orElseThrow(() -> new RuntimeException("Paciente no existe"));

        String type = documentTypeRepository.findByName(dto.getTypeName())
                .orElseThrow(() -> new RuntimeException("Tipo inválido"))
                .getName();
        JsonNode metadataJson = objectMapper.valueToTree(dto.getMetadata());

        Document document = new Document();
        document.setPatient(patient);
        document.setType(type);
        document.setFileUrl(dto.getFileUrl());
        document.setMetadata(metadataJson);
        document.setStatus("ACTIVO");

        documentRepository.save(document);
    }

}