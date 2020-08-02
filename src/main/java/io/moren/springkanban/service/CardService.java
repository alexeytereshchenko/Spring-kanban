package io.moren.springkanban.service;

import io.moren.springkanban.dto.CardDto;
import io.moren.springkanban.exception.ResourceNotFoundException;
import io.moren.springkanban.model.Card;
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

    public List<CardDto> getAll(Long columnId) {
        return columnRepository.findById(columnId).orElseThrow(ResourceNotFoundException::new)
                .getCards()
                .stream()
                .map(card -> {
                    CardDto cardDto = new CardDto();
                    cardDto.setId(card.getId());
                    cardDto.setName(card.getName());
                    return cardDto;
                })
                .collect(Collectors.toList());
    }

    public CardDto get(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return new CardDto(
                card.getId(),
                card.getName()
        );
    }

    public CardDto create(CardDto cardDto, Long columnId) {
        Card card = new Card();
        card.setId(cardDto.getId());
        card.setName(cardDto.getName());
        card.setColumn(
                columnRepository.findById(columnId).orElseThrow(ResourceNotFoundException::new)
        );

        cardRepository.save(card);
        cardDto.setId(card.getId());
        return cardDto;
    }

    public void update(Long id, CardDto cardDto, Long columnId) {
        Card card = cardRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        card.setName(cardDto.getName());
        card.setColumn(
                    columnRepository.findById(columnId).orElseThrow(ResourceNotFoundException::new)
            );
        cardRepository.save(card);
    }

    public void delete(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        cardRepository.delete(card);
    }

}
