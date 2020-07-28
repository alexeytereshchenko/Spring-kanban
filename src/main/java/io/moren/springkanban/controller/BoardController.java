package io.moren.springkanban.controller;

import io.moren.springkanban.dto.BoardDto;
import io.moren.springkanban.model.Board;
import io.moren.springkanban.model.User;
import io.moren.springkanban.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardDto> getAll(@AuthenticationPrincipal User user) {
        return boardService.getAll(user);
    }

    @GetMapping("{id}")
    public BoardDto get(@PathVariable Long id) {
        return boardService.get(id);
    }

    @PostMapping
    public BoardDto save(@RequestBody @Valid BoardDto board,
                         @AuthenticationPrincipal User user) {
        return boardService.save(board, user);
    }

    @PutMapping("{id}")
    public HttpStatus update(@RequestBody @Valid BoardDto board, @PathVariable Long id) {
        boardService.update(board, id);
        return HttpStatus.OK;
    }

    @DeleteMapping("{id}")
    public HttpStatus delete(@PathVariable Long id) {
        boardService.delete(id);
        return HttpStatus.OK;
    }
}
