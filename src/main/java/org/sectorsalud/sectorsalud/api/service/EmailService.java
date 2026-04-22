package org.sectorsalud.sectorsalud.api.service;

public interface EmailService {
    void sendTempPassword(String email, String tempPassword);
}
