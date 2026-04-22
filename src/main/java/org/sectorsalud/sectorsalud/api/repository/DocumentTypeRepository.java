package org.sectorsalud.sectorsalud.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sectorsalud.sectorsalud.api.entity.DocumentType;
import java.util.Optional;
import java.util.UUID;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, UUID> {
    Optional<DocumentType> findByName(String name);
    boolean existsByName(String name);
}