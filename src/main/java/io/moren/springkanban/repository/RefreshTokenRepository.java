package io.moren.springkanban.repository;

import io.moren.springkanban.model.RefreshToken;
import io.moren.springkanban.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUser(User user);
}
