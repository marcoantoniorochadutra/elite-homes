package com.elitehomes.core.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

public class JwtUtils {


    private static final String USER_AUTH_CLAIM = "user-auth";
    private static final String USER_IP_CLAIM = "user-ip";
    private static final String USER_APP_CLAIM = "user-app";
    private static final Integer TOKEN_VALIDITY = 5;
    private static final SecretKey CHAVE = Keys.hmacShaKeyFor("SzdThWLbUbLQDGWogQkDnpxVgITHibjrlrxyoZdk3JitJs7S9Wq975F5e8AWxyDA".getBytes(StandardCharsets.UTF_8));

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final Base64.Encoder BASE64_ENCODER = Base64.getUrlEncoder();

    public static String generate(LoginDto login, String application, String ipAddress) {
        Instant issuedAt = Instant.now();
        Instant expiration = issuedAt.plus(TOKEN_VALIDITY, ChronoUnit.MINUTES);
        return Jwts.builder()
                .claim(USER_AUTH_CLAIM, login)
                .claim(USER_IP_CLAIM, ipAddress)
                .claim(USER_APP_CLAIM, application)
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiration))
                .signWith(CHAVE)
                .compressWith(Jwts.ZIP.DEF)
                .compact();
    }

    public static LoginDto getApplicationLogin(String token) {
        Jws<Claims> jws = Jwts.parser()
                .verifyWith(CHAVE)
                .build()
                .parseSignedClaims(token);
            return null;
//        return mapper.map(jws.getPayload().get(USER_AUTH_CLAIM), LoginDto.class);
    }


    public static String generateRefreshToken() {
        byte[] randomBytes = new byte[32];
        SECURE_RANDOM.nextBytes(randomBytes);
        return BASE64_ENCODER.encodeToString(randomBytes);
    }
}
