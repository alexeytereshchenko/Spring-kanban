package io.moren.springkanban.service;

import io.moren.springkanban.model.Card;
import io.moren.springkanban.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    public Card get(Long id) {
        return cardRepository.findById(id).get();
    }

    public void create(@Valid Card card) {
        cardRepository.save(card);
    }

    public void update(@Valid Card card, Long id) {
        cardRepository.findById(id).ifPresent(oldCard -> {
            oldCard.setName(card.getName());
            cardRepository.save(oldCard);
        });
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

}
