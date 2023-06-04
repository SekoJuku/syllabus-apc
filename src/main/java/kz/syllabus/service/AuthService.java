package kz.syllabus.service;

import kz.syllabus.dto.request.user.LoginDtoRequest;
import kz.syllabus.entity.user.User;
import kz.syllabus.exception.domain.BadRequestException;
import kz.syllabus.security.JWTProvider;
import kz.syllabus.security.SecurityConstants;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthService {
    public static final String AUTHORIZATION = "Authorization";

    private final UserService userService;

    private final JWTProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public ResponseEntity<?> auth(LoginDtoRequest request) {
        User user = userService.findByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BadRequestException(SecurityConstants.ExceptionMessages.INVALID_CREDENTIALS);
        HttpHeaders authorizationHeader = getJwtHeader(user);
        return new ResponseEntity<>(user, authorizationHeader, HttpStatus.OK);
    }

    private HttpHeaders getJwtHeader(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORIZATION, jwtProvider.generateToken(user));
        return httpHeaders;
    }
}
