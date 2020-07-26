package io.moren.springkanban.service;

import io.moren.springkanban.model.Board;
import io.moren.springkanban.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    public Board get(Long id) {
        return boardRepository.findById(id).get();
    }

    public Board save(@Valid Board board) {
        return boardRepository.save(board);
    }

    public void update(@Valid Board board, Long id) {
        boardRepository.findById(id).ifPresent(oldBoard -> {
            oldBoard.setName(board.getName());
            boardRepository.save(oldBoard);
        });
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
