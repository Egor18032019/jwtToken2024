package com.springjwtsecurityexample.token.api;

import com.springjwtsecurityexample.token.model.JwtAuthenticationResponse;
import com.springjwtsecurityexample.token.model.SignInRequest;
import com.springjwtsecurityexample.token.model.SignUpRequest;
import com.springjwtsecurityexample.token.model.TokenRequest;
import com.springjwtsecurityexample.token.service.AuthenticationService;
import com.springjwtsecurityexample.token.service.JwtTokenService;
import com.springjwtsecurityexample.token.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = EndPoint.api + EndPoint.auth)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authentication Controller", description = "Контроллер для аутентификации и регистрации пользователей")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenService jwtService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping(EndPoint.register)
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping(EndPoint.login)
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    //todo убрать
    @PostMapping(EndPoint.refresh)
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody TokenRequest request) {
        // Проверка на валидность токена уже была произведене
        System.out.println("refresh");
        return ResponseEntity.ok(jwtService.refreshToken(request.getToken()));
    }
}
