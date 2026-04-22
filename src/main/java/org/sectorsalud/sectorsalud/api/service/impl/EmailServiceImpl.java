package org.sectorsalud.sectorsalud.api.service.impl;

import org.springframework.stereotype.Service;
import org.sectorsalud.sectorsalud.api.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendTempPassword(String email, String tempPassword) {

        System.out.println("====================================");
        System.out.println("📧 EMAIL SIMULADO ENVIADO");
        System.out.println("Para: " + email);
        System.out.println("Asunto: Código de acceso temporal");
        System.out.println("Código: " + tempPassword);
        System.out.println("Expira en: 10 minutos");
        System.out.println("====================================");
    }
}