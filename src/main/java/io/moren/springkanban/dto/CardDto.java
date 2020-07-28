package io.moren.springkanban.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long columnId;
}
