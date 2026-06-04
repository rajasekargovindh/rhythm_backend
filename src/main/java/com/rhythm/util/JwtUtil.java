package com.rhythm.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

    @Component
    public class JwtUtil {

        private static final String SECRET_KEY =
                "mySecretKeymySecretKeymySecretKey123456";

        private static final long EXPIRATION_TIME =
                1000 * 60 * 60 * 24;

        public String generateToken(String email) {

            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(
                            new Date(
                                    System.currentTimeMillis()
                                            + EXPIRATION_TIME
                            )
                    )
                    .signWith(
                            SignatureAlgorithm.HS256,
                            SECRET_KEY
                    )
                    .compact();
        }

        public String extractEmail(String token) {

            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        }

        public boolean validateToken(String token) {

            try {

                Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token);

                return true;

            } catch (Exception e) {

                return false;
            }
        }
    }



