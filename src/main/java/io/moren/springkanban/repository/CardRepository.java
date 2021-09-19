package io.moren.springkanban.repository;

import io.moren.springkanban.model.Card;
import io.moren.springkanban.model.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByColumn(Column column);

    Optional<Card> findByIdAndColumn(Long id, Column column);

    void deleteByIdAndColumn(Long id, Column column);
}
