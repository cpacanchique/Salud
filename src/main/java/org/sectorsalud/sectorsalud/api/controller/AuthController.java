package org.sectorsalud.sectorsalud.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.sectorsalud.sectorsalud.api.service.AuthService;
import org.sectorsalud.sectorsalud.api.dto.RequestPasswordDTO;
import org.sectorsalud.sectorsalud.api.dto.LoginDTO;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/request-password")
    public ResponseEntity<?> requestPassword(@RequestBody RequestPasswordDTO dto) {
        authService.requestPassword(dto.getCedula());
        return ResponseEntity.ok("Password enviado");
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        return ResponseEntity.ok(authService.login(dto));

    }

}
