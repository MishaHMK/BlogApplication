package michhmk.blogapp.security;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import michhmk.blogapp.dto.auth.UserLoginRequestDto;
import michhmk.blogapp.dto.auth.UserLoginResponseDto;
import michhmk.blogapp.model.User;
import michhmk.blogapp.repository.user.UserRepository;
import michhmk.blogapp.security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        User byEmail = userRepository.findByEmail(requestDto.email())
                .orElseThrow(
                        () -> new EntityNotFoundException("User with this email address does not exist")
                );

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password())
        );
        String token = jwtUtil.generateToken(authentication.getName(), byEmail.getId());
        return new UserLoginResponseDto(token);
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}
