package org.sectorsalud.sectorsalud.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "mi_clave_super_secreta_muy_larga_para_jwt_123456";

    private static final long EXPIRATION = 1000 * 60 * 30;

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // GENERAR TOKEN
    public static String generateToken(String cedula) {

        return Jwts.builder()
                .setSubject(cedula)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // VALIDAR TOKEN
    public static String getCedula(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}