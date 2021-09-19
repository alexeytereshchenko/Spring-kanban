package io.moren.springkanban.repository;

import io.moren.springkanban.model.Board;
import io.moren.springkanban.model.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColumnRepository extends JpaRepository<Column, Long> {
    Optional<Column> findByIdAndBoard(Long id, Board board);

    void deleteByIdAndBoard(Long id, Board board);
}
