package io.moren.springkanban.controller;

import io.moren.springkanban.dto.BoardDto;
import io.moren.springkanban.model.User;
import io.moren.springkanban.service.BoardService;
import io.swagger.v3.oas.annotations.Parameter;
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
    public ResponseEntity<List<BoardDto>> getAll(@Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
                boardService.getAll(user)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<BoardDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                boardService.get(id)
        );
    }

    @PostMapping
    public ResponseEntity<BoardDto> save(@RequestBody @Valid BoardDto board,
                                         @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                boardService.save(board, user)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid BoardDto board,
                             @PathVariable Long id) {
        boardService.update(board, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
