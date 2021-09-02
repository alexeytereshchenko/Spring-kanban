package io.moren.springkanban.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Data
public class RefreshToken {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne
//    @JoinColumn(nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private Instant expiryDate;
}
