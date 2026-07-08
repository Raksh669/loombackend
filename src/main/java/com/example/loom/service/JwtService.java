package com.example.loom.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.loom.entity.academicUser;
import com.example.loom.repository.academicuserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final academicuserRepository repo;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.exp}")
    private Long exp;

    public JwtService(academicuserRepository repo) {
        this.repo = repo;
    }

  
    public String generateToken(String email) {

        academicUser user = repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return Jwts.builder()
                .subject(email)
                .claim("role", user.getRole())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + exp))
                .signWith(getSecretKey())
                .compact();
    }

    
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

   
    public Claims extractAllClaim(String token) {

        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    
    public <T> T extractClaim(String token,Function<Claims, T> resolver) {

        return resolver.apply(extractAllClaim(token));
    }

    
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token,UserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    
    private boolean isTokenExpired(String token) {

        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}