package io.moren.springkanban.controller;

import io.moren.springkanban.dto.BoardDto;
import io.moren.springkanban.model.User;
import io.moren.springkanban.service.BoardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardDto>> getAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
                boardService.getAll(user)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<BoardDto> get(@AuthenticationPrincipal User user,
                                        @PathVariable Long id) {
        return ResponseEntity.ok(
                boardService.get(user, id)
        );
    }

    @PostMapping
    public ResponseEntity<BoardDto> save(@AuthenticationPrincipal User user,
                                         @RequestBody @Valid BoardDto board) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                boardService.save(user, board)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@AuthenticationPrincipal User user,
                                       @RequestBody @Valid BoardDto board,
                                       @PathVariable Long id) {
        boardService.update(user, board, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User user,
                                       @PathVariable Long id) {
        boardService.delete(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
