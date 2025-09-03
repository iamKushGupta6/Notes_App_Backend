package com.example.NotesApp.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
    private final Key key = Keys.hmacShaKeyFor("changeit-please-use-a-long-secret-key-which-is-at-least-32-bytes".getBytes());
    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+3600_000)).signWith(key).compact();
    }
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validate(String token) { try { extractUsername(token); return true; } catch(Exception e) { return false; } }
}
