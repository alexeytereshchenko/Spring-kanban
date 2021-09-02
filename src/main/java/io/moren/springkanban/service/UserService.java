package io.moren.springkanban.service;

import io.moren.springkanban.dto.UserDto;
import io.moren.springkanban.exception.UserAlreadyExistsException;
import io.moren.springkanban.model.Role;
import io.moren.springkanban.model.User;
import io.moren.springkanban.repository.RoleRepository;
import io.moren.springkanban.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(UserDto userDto) {

        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User " + userDto.getUsername() + " already exists!");
        }

        Role roleUser = roleRepository.findByName("ROLE_USER").get();

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(
                passwordEncoder.encode(userDto.getPassword())
        );
        user.setRoles(Collections.singleton(roleUser));

        userRepository.save(user);
    }

}
