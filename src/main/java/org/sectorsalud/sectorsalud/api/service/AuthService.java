package org.sectorsalud.sectorsalud.api.service;

import org.sectorsalud.sectorsalud.api.dto.LoginDTO;
public interface AuthService {
    void requestPassword(String cedula);
    String login(LoginDTO dto);
}
