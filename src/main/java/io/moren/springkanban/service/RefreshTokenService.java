package io.moren.springkanban.service;

import io.moren.springkanban.model.RefreshToken;
import io.moren.springkanban.model.User;
import io.moren.springkanban.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.expirationMs}")
    private int expire;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken create(User user) {
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setExpiryDate(Instant.now().plusMillis(expire));
        token.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.save(token);
    }

    public RefreshToken update(RefreshToken oldToken) {
        RefreshToken token = new RefreshToken();
        token.setId(oldToken.getId());
        token.setUser(oldToken.getUser());
        token.setExpiryDate(Instant.now().plusMillis(expire));
        token.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.save(token);
    }

    public void verifyExpiration(RefreshToken token) {
        if (Instant.now().isAfter(token.getExpiryDate())) {
            refreshTokenRepository.delete(token);
            throw new CredentialsExpiredException("Token was expired. Please make a new login request!");
        }
    }
}
