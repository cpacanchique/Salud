package org.sectorsalud.sectorsalud.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;

import org.sectorsalud.sectorsalud.api.repository.PatientRepository;
import org.sectorsalud.sectorsalud.api.entity.Patient;

import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Patient patient) {

        if (patientRepository.findByCedula(patient.getCedula()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("El paciente ya existe");
        }

        patient.setId(UUID.randomUUID());

        patientRepository.save(patient);

        return ResponseEntity.ok("Paciente creado correctamente");
    }
}