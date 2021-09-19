package io.moren.springkanban;

import io.moren.springkanban.model.Role;
import io.moren.springkanban.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKanbanApplication implements ApplicationRunner {

    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringKanbanApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        if (roleRepository.findAll().isEmpty()) {
            Role user = new Role();
            user.setName("ROLE_USER");
            roleRepository.save(user);
        }
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
