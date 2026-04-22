package org.sectorsalud.sectorsalud.api.service.impl;

import org.springframework.stereotype.Service;
import org.sectorsalud.sectorsalud.api.service.AuthService;
import org.sectorsalud.sectorsalud.api.repository.PatientRepository;
import org.sectorsalud.sectorsalud.api.entity.Patient;
import org.sectorsalud.sectorsalud.api.dto.LoginDTO;
import org.sectorsalud.sectorsalud.api.service.EmailService;
import java.time.LocalDateTime;
import java.util.UUID;
import org.sectorsalud.sectorsalud.api.security.JwtUtil;
@Service
public class AuthServiceImpl implements AuthService {

    private final PatientRepository patientRepository;
    private final EmailService emailService;
    public AuthServiceImpl(PatientRepository patientRepository,
                           EmailService emailService) {
        this.patientRepository = patientRepository;
        this.emailService = emailService;
    }
    // =========================================
    // 1. SOLICITAR PASSWORD TEMPORAL
    // =========================================
    @Override
    public void requestPassword(String cedula) {

        Patient patient = patientRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // generar password temporal
        String tempPassword = UUID.randomUUID()
                .toString()
                .substring(0, 6);

        // guardar en BD
        patient.setTempPassword(tempPassword);
        patient.setTempPasswordExpiry(LocalDateTime.now().plusMinutes(10));

        patientRepository.save(patient);

        // ENVÍO DE CORREO SIMULADO
        emailService.sendTempPassword(patient.getEmail(), tempPassword);
    }

    // =========================================
    // 2. LOGIN
    // =========================================
    @Override
    public String login(LoginDTO dto) {

        Patient patient = patientRepository.findByCedula(dto.getCedula())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // validar password
        if (patient.getTempPassword() == null ||
                !patient.getTempPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Password incorrecto");
        }

        // validar expiración
        if (patient.getTempPasswordExpiry() == null ||
                patient.getTempPasswordExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Password expirado");
        }

        // limpiar password (importante seguridad)
        patient.setTempPassword(null);
        patient.setTempPasswordExpiry(null);
        patientRepository.save(patient);

        // JWT
        return JwtUtil.generateToken(patient.getCedula());
    }
}