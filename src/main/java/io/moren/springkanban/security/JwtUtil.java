package io.moren.springkanban.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.moren.springkanban.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();

        return Jwts
                .builder()
                .setSubject(principal.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsername(String token) {

            return Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(
                            // delete 'Bearer ' from token
                            token.substring(JwtProperties.PREFIX.length())
                    )
                    .getBody()
                    .getSubject();
    }
}
