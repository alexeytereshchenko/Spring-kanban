package io.moren.springkanban.repository;

import io.moren.springkanban.model.Board;
import io.moren.springkanban.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByIdAndUser(Long id, User user);

    void deleteByIdAndUser(Long id, User user);
}
