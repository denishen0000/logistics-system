package com.logistics.system.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "MySuperSecureSecretKeyThatIsAtLeast32CharsLong123!"; // use env variable in prod
    private final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hour

    // -------------------- Generate token --------------------
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .addClaims(Map.of("role", role)) // add role claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // -------------------- Validate token --------------------
    public boolean validateToken(String token, String email) {
        String tokenEmail = extractEmail(token);
        return (tokenEmail.equals(email) && !isTokenExpired(token));
    }

    // -------------------- Extract email --------------------
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // -------------------- Extract role --------------------
    public String extractUserRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // -------------------- Check expiration --------------------
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // -------------------- Extract claims --------------------
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
