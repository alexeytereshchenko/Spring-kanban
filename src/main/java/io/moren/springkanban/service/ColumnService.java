package io.moren.springkanban.service;

import io.moren.springkanban.model.Column;
import io.moren.springkanban.repository.ColumnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;

    public List<Column> getAll() {
        return columnRepository.findAll();
    }

    public Column get(Long id) {
        return columnRepository.findById(id).get();
    }

    public Column save(@Valid Column column) {
        return columnRepository.save(column);
    }

    public void update(@Valid Column column, Long id) {
        columnRepository.findById(id).ifPresent(oldColumn -> {
            oldColumn.setName(column.getName());
            columnRepository.save(oldColumn);
        });
    }

    public void delete(Long id) {
        columnRepository.deleteById(id);
    }
}
