package io.moren.springkanban.controller;

import io.moren.springkanban.dto.ColumnDto;
import io.moren.springkanban.service.ColumnService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/boards/{boardId}/columns")
public class ColumnController {

    private final ColumnService columnService;

    @GetMapping
    public ResponseEntity<List<ColumnDto>> getAll(@PathVariable Long boardId) {
        return ResponseEntity.ok(
                columnService.getAll(boardId)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<ColumnDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                columnService.get(id)
        );
    }

    @PostMapping
    public ResponseEntity<ColumnDto> save(@RequestBody @Valid ColumnDto columnDto,
                                          @PathVariable Long boardId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                columnService.save(columnDto, boardId)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid ColumnDto columnDto,
                             @PathVariable Long id) {
        columnService.update(columnDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        columnService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
