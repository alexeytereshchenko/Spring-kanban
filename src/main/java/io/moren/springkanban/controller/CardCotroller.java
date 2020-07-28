package io.moren.springkanban.controller;

import io.moren.springkanban.dto.CardDto;
import io.moren.springkanban.model.User;
import io.moren.springkanban.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/card")
public class CardCotroller {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardDto>> getAll(@AuthenticationPrincipal User user,
                                                @RequestParam Long columnId) {
        return ResponseEntity
                .ok(cardService.getAll(columnId, user));
    }

    @GetMapping("{id}")
    public ResponseEntity<CardDto> get(@PathVariable Long id) {
        return ResponseEntity
                .ok(cardService.get(id));
    }

    @PostMapping
    public ResponseEntity<CardDto> save(@RequestBody @Valid CardDto cardDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cardService.create(cardDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid CardDto cardDto) {
        cardService.update(id, cardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
