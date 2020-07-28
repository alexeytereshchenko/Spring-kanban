package io.moren.springkanban.service;

import io.moren.springkanban.dto.ColumnDto;
import io.moren.springkanban.model.Board;
import io.moren.springkanban.model.Column;
import io.moren.springkanban.model.User;
import io.moren.springkanban.repository.BoardRepository;
import io.moren.springkanban.repository.ColumnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;

    public List<ColumnDto> getAll(Long boardId) {
        return boardRepository.findById(boardId).get()
                .getColumns()
                .stream()
                .map(column -> {
                    return new ColumnDto(
                            column.getId(),
                            boardId,
                            column.getName());
                })
                .collect(Collectors.toList());
    }

    public ColumnDto get(Long id) {

        Column column =  columnRepository.findById(id).get();

        ColumnDto columnDto = new ColumnDto();
        columnDto.setBoardId(column.getBoard().getId());
        columnDto.setId(column.getId());
        columnDto.setName(column.getName());

        return columnDto;
    }

    public ColumnDto save(ColumnDto columnDto) {

        Column column = new Column();
        column.setName(columnDto.getName());
        column.setBoard(
                boardRepository.findById(columnDto.getBoardId()).get()
        );

        columnRepository.save(column);
        columnDto.setId(column.getId());
        return columnDto;
    }

    public void update(ColumnDto columnDto, Long id) {
        columnRepository.findById(id).ifPresent(column -> {
            column.setName(columnDto.getName());
            columnRepository.save(column);
        });
    }

    public void delete(Long id) {
        columnRepository.deleteById(id);
    }
}
