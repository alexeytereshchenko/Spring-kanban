package io.moren.springkanban.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "boards")
@Data
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "board", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Column> columns;
}
