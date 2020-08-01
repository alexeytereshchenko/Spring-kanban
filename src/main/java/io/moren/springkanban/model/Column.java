package io.moren.springkanban.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "columns")
@Data
public class Column {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "column", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Card> cards;
}
