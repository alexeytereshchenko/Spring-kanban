package io.moren.springkanban.controller;

import io.moren.springkanban.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

}
