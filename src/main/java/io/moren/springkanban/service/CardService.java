package io.moren.springkanban.service;

import io.moren.springkanban.dto.CardDto;
import io.moren.springkanban.model.Card;
import io.moren.springkanban.model.User;
import io.moren.springkanban.repository.CardRepository;
import io.moren.springkanban.repository.ColumnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ColumnRepository columnRepository;

    public List<CardDto> getAll(Long columndId, User user) {
        return columnRepository.findById(columndId).get()
                .getCards()
                .stream()
                .map(card -> {
                    CardDto cardDto = new CardDto();
                    cardDto.setId(card.getId());
                    cardDto.setName(card.getName());
                    cardDto.setColumnId(card.getColumn().getId());
                    return cardDto;
                })
                .collect(Collectors.toList());
    }

    public CardDto get(Long id) {
        Card card = cardRepository.findById(id).get();
        return new CardDto(
                card.getId(),
                card.getName(),
                card.getColumn().getId()
        );
    }

    public CardDto create(@Valid CardDto cardDto) {
        Card card = new Card();
        card.setId(cardDto.getId());
        card.setName(cardDto.getName());
        card.setColumn(
                columnRepository.findById(cardDto.getColumnId()).get()
        );

        cardRepository.save(card);
        cardDto.setId(card.getId());
        return cardDto;
    }

    public void update(Long id, @Valid CardDto cardDto) {
        cardRepository.findById(id).ifPresent(oldCard -> {
            oldCard.setName(cardDto.getName());
            oldCard.setColumn(
                    columnRepository.findById(cardDto.getColumnId()).get()
            );
            cardRepository.save(oldCard);
        });
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

}
