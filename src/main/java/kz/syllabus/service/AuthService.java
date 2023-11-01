package kz.syllabus.service;

import kz.syllabus.dto.request.TokenDtoRequest;
import kz.syllabus.dto.request.user.LoginDtoRequest;
import kz.syllabus.dto.response.TokenDtoResponse;
import kz.syllabus.service.user.UserService;
import kz.syllabus.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final JwtDecoder jwtDecoder;

    @SneakyThrows
    public TokenDtoResponse auth(LoginDtoRequest request) {
        var user = userService.findByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Invalid password");
        var accessToken = jwtUtil.generateAccessToken(user);
        var refreshToken = jwtUtil.generateRefreshToken(user);
        return new TokenDtoResponse(accessToken, refreshToken);
    }

    @SneakyThrows
    public TokenDtoResponse refresh(TokenDtoRequest request) {
        var subject = jwtDecoder.decode(request.refreshToken()).getSubject();
        var user = userService.findByUsername(subject);
        return new TokenDtoResponse(jwtUtil.generateAccessToken(user), jwtUtil.generateRefreshToken(user));
    }
}
