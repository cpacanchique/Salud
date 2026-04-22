package org.sectorsalud.sectorsalud.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sectorsalud.sectorsalud.api.entity.Document;
import org.sectorsalud.sectorsalud.api.entity.Patient;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
    List<Document> findByPatient(Patient patient);
}
