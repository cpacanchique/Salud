package org.sectorsalud.sectorsalud.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.sectorsalud.sectorsalud.api.entity.Patient;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByCedula(String cedula);
}
