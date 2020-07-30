package io.moren.springkanban.controller;

import io.moren.springkanban.dto.ColumnDto;
import io.moren.springkanban.model.User;
import io.moren.springkanban.service.ColumnService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/boards/{boardId}/columns")
public class ColumnController {

    private final ColumnService columnService;

    @GetMapping
    public List<ColumnDto> getAll(@PathVariable Long boardId,
                                  @AuthenticationPrincipal User user) {
        return columnService.getAll(boardId);
    }

    @GetMapping("{id}")
    public ColumnDto get(@PathVariable Long id) {
        return columnService.get(id);
    }

    @PostMapping
    public ColumnDto save(@RequestBody @Valid ColumnDto columnDto) {
        return columnService.save(columnDto);
    }

    @PutMapping("{id}")
    public HttpStatus update(@RequestBody @Valid ColumnDto columnDto,
                             @PathVariable Long id) {
        columnService.update(columnDto, id);
        return HttpStatus.OK;
    }

    @DeleteMapping("{id}")
    public HttpStatus delete(@PathVariable Long id) {
        columnService.delete(id);
        return HttpStatus.OK;
    }
}
