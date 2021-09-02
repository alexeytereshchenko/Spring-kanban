package io.moren.springkanban.controller;

import io.moren.springkanban.dto.TokenDto;
import io.moren.springkanban.dto.UserDto;
import io.moren.springkanban.model.RefreshToken;
import io.moren.springkanban.security.JwtProperties;
import io.moren.springkanban.security.JwtUtil;
import io.moren.springkanban.service.AuthService;
import io.moren.springkanban.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid UserDto userDto) {
        TokenDto token = authService.login(userDto);

        return ResponseEntity.ok(token);
    }

    @PostMapping("signup")
    public ResponseEntity<TokenDto> signup(@RequestBody @Valid UserDto userDto) {
        authService.signup(userDto);

        TokenDto token = authService.login(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<TokenDto> refreshToken(@RequestBody RefreshTokenRequest tokenRequest) {
        RefreshToken oldRefreshToken = refreshTokenService
                .findByToken(
                        tokenRequest.getToken()
                ).orElseThrow(() -> new CredentialsExpiredException("Incorrect refresh token"));

        refreshTokenService.verifyExpiration(oldRefreshToken);

        RefreshToken newRefreshToken = refreshTokenService.update(oldRefreshToken);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setUsername(newRefreshToken.getUser().getUsername());
        tokenDto.setType(JwtProperties.PREFIX);
        tokenDto.setExpireTime(newRefreshToken.getExpiryDate().getEpochSecond());
        tokenDto.setAccessToken(jwtUtil.generateToken(newRefreshToken.getUser()));
        tokenDto.setRefreshToken(newRefreshToken.getToken());

        return ResponseEntity.ok(tokenDto);
    }
}

@Data
class RefreshTokenRequest {
    private String token;
}
