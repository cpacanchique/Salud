package org.sectorsalud.sectorsalud.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "document_types")
public class DocumentType {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name; // CONCEPTO_MEDICO, PARACLINICO, etc.
}