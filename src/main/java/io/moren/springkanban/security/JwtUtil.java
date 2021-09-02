package io.moren.springkanban.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.moren.springkanban.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationMs}")
    private int expire;

    public String generateToken(User user) {
        String username = user.getUsername();
        return Jwts
                .builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(createDate(expire))
                .compact();
    }

    private Date createDate(int expire) {
        return new Date(
                (new Date()).getTime() + expire
        );
    }

    public String getUsername(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(
                            token.replace(JwtProperties.PREFIX, "")
                    )
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new AuthenticationServiceException("Incorrect token!");
        }
    }

    public boolean validate(String token) {
        token = token.replace(JwtProperties.PREFIX, "");
        try {
            Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationServiceException("Incorrect token!");
        }
    }
}
