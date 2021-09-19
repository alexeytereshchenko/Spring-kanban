package io.moren.springkanban.service;

import io.moren.springkanban.dto.CardDto;
import io.moren.springkanban.exception.ResourceNotFoundException;
import io.moren.springkanban.model.Board;
import io.moren.springkanban.model.Card;
import io.moren.springkanban.model.Column;
import io.moren.springkanban.model.User;
import io.moren.springkanban.repository.BoardRepository;
import io.moren.springkanban.repository.CardRepository;
import io.moren.springkanban.repository.ColumnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardService {

    private final BoardRepository boardRepository;
    private final ColumnRepository columnRepository;
    private final CardRepository cardRepository;

    public List<CardDto> getAll(User user, Long boardId, Long columnId) {
        Board board = boardRepository.findByIdAndUser(boardId, user).orElseThrow(ResourceNotFoundException::new);
        Column column = columnRepository.findByIdAndBoard(columnId, board).orElseThrow(ResourceNotFoundException::new);
        return cardRepository.findByColumn(column)
                .stream()
                .map(card -> {
                    CardDto cardDto = new CardDto();
                    cardDto.setId(card.getId());
                    cardDto.setName(card.getName());
                    return cardDto;
                })
                .collect(Collectors.toList());
    }

    public CardDto get(User user, Long boardId, Long columnId, Long id) {
        Board board = boardRepository.findByIdAndUser(boardId, user).orElseThrow(ResourceNotFoundException::new);
        Column column = columnRepository.findByIdAndBoard(columnId, board).orElseThrow(ResourceNotFoundException::new);
        Card card = cardRepository.findByIdAndColumn(id, column).orElseThrow(ResourceNotFoundException::new);
        return new CardDto(
                card.getId(),
                card.getName());
    }

    public CardDto create(User user, Long boardId, Long columnId, CardDto cardDto) {
        Board board = boardRepository.findByIdAndUser(boardId, user).orElseThrow(ResourceNotFoundException::new);
        Column column = columnRepository.findByIdAndBoard(columnId, board).orElseThrow(ResourceNotFoundException::new);

        Card card = new Card();
        card.setId(cardDto.getId());
        card.setName(cardDto.getName());
        card.setColumn(column);
        cardRepository.save(card);

        cardDto.setId(card.getId());
        return cardDto;
    }

    public void update(User user, Long boardId, Long columnId, Long id, CardDto cardDto) {
        Board board = boardRepository.findByIdAndUser(boardId, user).orElseThrow(ResourceNotFoundException::new);
        Column column = columnRepository.findByIdAndBoard(columnId, board).orElseThrow(ResourceNotFoundException::new);
        Card card = cardRepository.findByIdAndColumn(id, column).orElseThrow(ResourceNotFoundException::new);

        card.setName(cardDto.getName());
        card.setColumn(column);
        cardRepository.save(card);
    }

    @Transactional
    public void delete(User user, Long boardId, Long columnId, Long id) {
        Board board = boardRepository.findByIdAndUser(boardId, user).orElseThrow(ResourceNotFoundException::new);
        Column column = columnRepository.findByIdAndBoard(columnId, board).orElseThrow(ResourceNotFoundException::new);
        Card card = cardRepository.findByIdAndColumn(id, column).orElseThrow(ResourceNotFoundException::new);
        cardRepository.deleteByIdAndColumn(card.getId(), column);
    }

}
