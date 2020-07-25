package io.moren.springkanban.service;

import io.moren.springkanban.dto.LoginDto;
import io.moren.springkanban.dto.RegistrationDto;
import io.moren.springkanban.model.Role;
import io.moren.springkanban.model.User;
import io.moren.springkanban.repository.RoleRepository;
import io.moren.springkanban.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }

    public void save(RegistrationDto registrationDto) {

        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()
                || registrationDto.getUsername() == null) {
            return;
        }

        Role roleUser = roleRepository.findByName("ROLE_USER").get();

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        user.setRoles(Collections.singleton(roleUser));

        userRepository.save(user);
    }

    // update method
}
