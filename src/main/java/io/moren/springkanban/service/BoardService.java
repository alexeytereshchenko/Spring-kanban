package io.moren.springkanban.service;

import io.moren.springkanban.dto.BoardDto;
import io.moren.springkanban.exception.ResourceNotFoundException;
import io.moren.springkanban.model.Board;
import io.moren.springkanban.model.User;
import io.moren.springkanban.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDto> getAll(User user) {
        return user.getBoards()
                .stream()
                .map(board -> {
                    return new BoardDto(board.getId(), board.getName());
                })
                .collect(Collectors.toList());
    }

    public BoardDto get(User user, Long id) {
        Board board = boardRepository.findByIdAndUser(id, user).orElseThrow(ResourceNotFoundException::new);
        return new BoardDto(board.getId(), board.getName());
    }

    public BoardDto save(User user, BoardDto boardDto) {
        Board board = new Board();
        board.setName(boardDto.getName());
        board.setUser(user);
        boardRepository.save(board);

        boardDto.setId(board.getId());
        return boardDto;
    }

    public void update(User user, BoardDto boardDto, Long id) {
        Board board = boardRepository.findByIdAndUser(id, user).orElseThrow(ResourceNotFoundException::new);
        board.setName(boardDto.getName());
        boardRepository.save(board);
    }

    @Transactional
    public void delete(User user, Long id) {
        boardRepository.deleteByIdAndUser(id, user);
    }
}
