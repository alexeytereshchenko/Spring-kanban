package io.moren.springkanban.service;

import io.moren.springkanban.dto.ColumnDto;
import io.moren.springkanban.exception.ResourceNotFoundException;
import io.moren.springkanban.model.Column;
import io.moren.springkanban.repository.BoardRepository;
import io.moren.springkanban.repository.ColumnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;

    public List<ColumnDto> getAll(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(ResourceNotFoundException::new)
                .getColumns()
                .stream()
                .map(column -> {
                    return new ColumnDto(
                            column.getId(),
                            column.getName());
                })
                .collect(Collectors.toList());
    }

    public ColumnDto get(Long id) {

        Column column =  columnRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        ColumnDto columnDto = new ColumnDto();
        columnDto.setId(column.getId());
        columnDto.setName(column.getName());

        return columnDto;
    }

    public ColumnDto save(ColumnDto columnDto, Long boardId) {

        Column column = new Column();
        column.setName(columnDto.getName());
        column.setBoard(
                boardRepository.findById(boardId).orElseThrow(ResourceNotFoundException::new)
        );

        columnRepository.save(column);
        columnDto.setId(column.getId());
        return columnDto;
    }

    public void update(ColumnDto columnDto, Long id) {
        Column column = columnRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        column.setName(columnDto.getName());
        columnRepository.save(column);
    }

    public void delete(Long id) {
        Column column = columnRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        columnRepository.delete(column);
    }
}
