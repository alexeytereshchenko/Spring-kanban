package io.moren.springkanban.service;

import io.moren.springkanban.dto.ColumnDto;
import io.moren.springkanban.exception.ResourceNotFoundException;
import io.moren.springkanban.model.Board;
import io.moren.springkanban.model.Column;
import io.moren.springkanban.model.User;
import io.moren.springkanban.repository.BoardRepository;
import io.moren.springkanban.repository.ColumnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;

    public List<ColumnDto> getAll(User user, Long boardId) {
        return boardRepository.findByIdAndUser(boardId, user).orElseThrow(ResourceNotFoundException::new)
                .getColumns()
                .stream()
                .map(column -> {
                    return new ColumnDto(
                            column.getId(),
                            column.getName()
                    );
                })
                .collect(Collectors.toList());
    }

    public ColumnDto get(User user, Long boardId, Long id) {
        Board board = boardRepository.findByIdAndUser(boardId, user).orElseThrow(ResourceNotFoundException::new);
        Column column = columnRepository.findByIdAndBoard(id, board).orElseThrow(ResourceNotFoundException::new);

        ColumnDto columnDto = new ColumnDto();
        columnDto.setId(column.getId());
        columnDto.setName(column.getName());

        return columnDto;
    }

    public ColumnDto save(User user, Long boardId, ColumnDto columnDto) {
        Column column = new Column();
        column.setName(columnDto.getName());
        column.setBoard(
                boardRepository.findByIdAndUser(boardId, user).orElseThrow(ResourceNotFoundException::new)
        );

        columnRepository.save(column);
        columnDto.setId(column.getId());
        return columnDto;
    }

    public void update(User user, Long id, Long boardId, ColumnDto columnDto) {
        Board board = boardRepository.findByIdAndUser(boardId, user).orElseThrow(ResourceNotFoundException::new);
        Column column = columnRepository.findByIdAndBoard(id, board).orElseThrow(ResourceNotFoundException::new);
        column.setName(columnDto.getName());
        columnRepository.save(column);
    }

    @Transactional
    public void delete(User user, Long id, Long boardId) {
        Board board = boardRepository.findByIdAndUser(boardId, user).orElseThrow(ResourceNotFoundException::new);
        Column column = columnRepository.findByIdAndBoard(id, board).orElseThrow(ResourceNotFoundException::new);
        columnRepository.deleteByIdAndBoard(column.getId(), board);
    }
}
