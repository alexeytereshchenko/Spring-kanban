package io.moren.springkanban.controller;

import io.moren.springkanban.dto.TokenDto;
import io.moren.springkanban.dto.UserDto;
import io.moren.springkanban.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto userDto) {
        TokenDto token = authService.login(userDto);

        return ResponseEntity.ok(token);
    }
    
    @PostMapping("signup")
    public ResponseEntity<TokenDto> signup(@RequestBody UserDto userDto) {
        authService.signup(userDto);

        TokenDto token = authService.login(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
