package io.moren.springkanban.service;

import io.moren.springkanban.dto.BoardDto;
import io.moren.springkanban.model.Board;
import io.moren.springkanban.model.User;
import io.moren.springkanban.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public BoardDto get(Long id, User user) {
        Board board = boardRepository.findByIdAndUser(id, user).get();
        return new BoardDto(board.getId(), board.getName());
    }

    public BoardDto save(BoardDto boardDto, User user) {
        Board board = new Board();
        board.setName(boardDto.getName());
        board.setUser(user);

        boardRepository.save(board);
        boardDto.setId(board.getId());
        return boardDto;
    }

    public void update(BoardDto board, Long id, User user) {
        boardRepository.findByIdAndUser(id, user).ifPresent(oldBoard -> {
            oldBoard.setName(board.getName());
            boardRepository.save(oldBoard);
        });
    }

    public void delete(Long id, User user) {
        boardRepository.deleteByIdAndUser(id, user);
    }
}
