package kz.syllabus.controller;

import kz.syllabus.dto.request.TokenDtoRequest;
import kz.syllabus.dto.request.user.LoginDtoRequest;
import kz.syllabus.dto.response.TokenDtoResponse;
import kz.syllabus.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody LoginDtoRequest loginDtoRequest) {
        return toResponse(authService.auth(loginDtoRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(TokenDtoRequest request) {
        return toResponse(authService.refresh(request));
    }

    private ResponseEntity<?> toResponse(TokenDtoResponse response) {
        return new ResponseEntity<>(response, getJwtHeader(response.accessToken()), HttpStatus.OK);
    }

    private HttpHeaders getJwtHeader(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        return httpHeaders;
    }
}
