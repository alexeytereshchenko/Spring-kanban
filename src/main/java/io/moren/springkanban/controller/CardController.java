package io.moren.springkanban.controller;

import io.moren.springkanban.dto.CardDto;
import io.moren.springkanban.model.User;
import io.moren.springkanban.service.CardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<CardDto>> getAll(@AuthenticationPrincipal User user,
                                                @PathVariable Long boardId,
                                                @PathVariable Long columnId) {
        return ResponseEntity
                .ok(cardService.getAll(user, boardId, columnId));
    }

    @GetMapping("{id}")
    public ResponseEntity<CardDto> get(@AuthenticationPrincipal User user,
                                       @PathVariable Long boardId,
                                       @PathVariable Long columnId,
                                       @PathVariable Long id) {
        return ResponseEntity
                .ok(cardService.get(user, boardId, columnId, id));
    }

    @PostMapping
    public ResponseEntity<CardDto> save(@AuthenticationPrincipal User user,
                                        @PathVariable Long boardId,
                                        @PathVariable Long columnId,
                                        @RequestBody @Valid CardDto cardDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cardService.create(user, boardId, columnId, cardDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@AuthenticationPrincipal User user,
                                       @PathVariable Long boardId,
                                       @PathVariable Long columnId,
                                       @PathVariable Long id,
                                       @RequestBody @Valid CardDto cardDto) {
        cardService.update(user, boardId, columnId, id, cardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User user,
                                       @PathVariable Long boardId,
                                       @PathVariable Long columnId,
                                       @PathVariable Long id) {
        cardService.delete(user, boardId, columnId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
