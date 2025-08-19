package michhmk.blogapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import michhmk.blogapp.dto.auth.UserLoginRequestDto;
import michhmk.blogapp.dto.auth.UserLoginResponseDto;
import michhmk.blogapp.dto.auth.UserRegisterRequestDto;
import michhmk.blogapp.dto.auth.UserRegisterResponseDto;
import michhmk.blogapp.security.AuthenticationService;
import michhmk.blogapp.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth controller", description = "User register and authorization endpoint")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authService;

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Register new user in system with provided data")
    public UserRegisterResponseDto register(
            @Valid @RequestBody UserRegisterRequestDto registerRequestDto) {
        return userService.save(registerRequestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Login as user to receive JWT Token")
    public UserLoginResponseDto login(
            @Valid @RequestBody UserLoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }
}
