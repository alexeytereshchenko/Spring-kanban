package io.moren.springkanban.controller;

import io.moren.springkanban.dto.LoginDto;
import io.moren.springkanban.dto.RegistrationDto;
import io.moren.springkanban.dto.TokenDto;
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
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        TokenDto token = authService.login(loginDto);

        return ResponseEntity.ok(token);
    }

    @PostMapping("signup")
    public HttpStatus signup(@RequestBody RegistrationDto registrationDto) {
        authService.signup(registrationDto);

        return HttpStatus.CREATED;
    }
}
