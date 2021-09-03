package io.moren.springkanban.controller;

import io.moren.springkanban.dto.CardDto;
import io.moren.springkanban.service.CardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/boards/{boardId}/columns/{columnId}/cards")
@SecurityRequirement(name = "bearerAuth")
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardDto>> getAll(@PathVariable Long columnId) {
        return ResponseEntity
                .ok(cardService.getAll(columnId));
    }

    @GetMapping("{id}")
    public ResponseEntity<CardDto> get(@PathVariable Long id) {
        return ResponseEntity
                .ok(cardService.get(id));
    }

    @PostMapping
    public ResponseEntity<CardDto> save(@RequestBody @Valid CardDto cardDto,
                                        @PathVariable Long columnId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cardService.create(cardDto, columnId));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid CardDto cardDto,
                                       @PathVariable Long columnId) {
        cardService.update(id, cardDto, columnId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
