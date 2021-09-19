package io.moren.springkanban.controller;

import io.moren.springkanban.dto.ColumnDto;
import io.moren.springkanban.model.User;
import io.moren.springkanban.service.ColumnService;
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
@RequestMapping("/api/boards/{boardId}/columns")
@SecurityRequirement(name = "bearerAuth")
public class ColumnController {

    private final ColumnService columnService;

    @GetMapping
    public ResponseEntity<List<ColumnDto>> getAll(@AuthenticationPrincipal User user,
                                                  @PathVariable Long boardId) {
        return ResponseEntity.ok(
                columnService.getAll(user, boardId)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<ColumnDto> get(@AuthenticationPrincipal User user,
                                         @PathVariable Long boardId,
                                         @PathVariable Long id) {
        return ResponseEntity.ok(
                columnService.get(user, boardId, id)
        );
    }

    @PostMapping
    public ResponseEntity<ColumnDto> save(@AuthenticationPrincipal User user,
                                          @PathVariable Long boardId,
                                          @RequestBody @Valid ColumnDto columnDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                columnService.save(user, boardId, columnDto)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@AuthenticationPrincipal User user,
                                       @PathVariable Long boardId,
                                       @PathVariable Long id,
                                       @RequestBody @Valid ColumnDto columnDto) {
        columnService.update(user, id, boardId, columnDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User user,
                                       @PathVariable Long boardId,
                                       @PathVariable Long id) {
        columnService.delete(user, id, boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
