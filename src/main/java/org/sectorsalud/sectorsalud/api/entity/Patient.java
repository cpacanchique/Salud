package org.sectorsalud.sectorsalud.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String cedula;

    @Column(nullable = false)
    private String email;

    private String passwordHash;

     //NUEVOS CAMPOS (LOGIN TEMPORAL)
    private String tempPassword;

    private LocalDateTime tempPasswordExpiry;

    //  AUTOMÁTICO EN BASE DE DATOS
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}