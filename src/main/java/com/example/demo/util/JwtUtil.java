package com.example.demo.util;

import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
@Deprecated
public class JwtUtil {

    private static String SECRET_KEY = "ThisIsASecretKeyThisIsASecretKeyThisIsASecretKey";
    private static long EXPIRATION = 24 * 60 * 60 * 1000L;

    // Decode
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    public String extractUserNames(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiry(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    // Generation// Encoding Token
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, SECRET_KEY).setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)).compact();
    }

    // Validation
    public Boolean validateToken(String token, UserDetails user) {
        String userName = extractUserNames(token);
        Date expiry = extractExpiry(token);

        return (userName.equals(user.getUsername()) && expiry.after(new Date(System.currentTimeMillis())));
    }

    // public static void main(String[] args) {
    // UserDetails user = new User("Gurpreet", "123", Set.of(new
    // SimpleGrantedAuthority("ADMIN")));
    // JwtUtil jwtUtil = new JwtUtil();
    // String token = jwtUtil.generateToken(user);
    // System.err.println(token);
    // System.err.println(jwtUtil.extractUserNames(token));
    // System.err.println(jwtUtil.extractExpiry(token));
    // System.err.println(jwtUtil.validateToken(token, user));

    // };
}

