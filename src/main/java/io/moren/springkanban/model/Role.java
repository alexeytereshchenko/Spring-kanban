package io.moren.springkanban.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Role name is required")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}
