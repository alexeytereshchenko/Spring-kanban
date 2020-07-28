package io.moren.springkanban.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnDto {

    private Long id;

    @NotNull
    private Long boardId;

    @NotNull
    private String name;
}
