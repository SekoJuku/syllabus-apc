package kz.syllabus.common.controller;

import kz.syllabus.common.dto.request.TokenDtoRequest;
import kz.syllabus.common.dto.request.user.LoginDtoRequest;
import kz.syllabus.common.dto.response.TokenDtoResponse;
import kz.syllabus.common.persistence.model.PersonalInfo;
import kz.syllabus.common.service.AuthService;
import kz.syllabus.common.service.user.PersonalInfoService;
import kz.syllabus.common.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Log
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final PersonalInfoService personalInfoService;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody LoginDtoRequest loginDtoRequest) {
        return toResponse(authService.auth(loginDtoRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(TokenDtoRequest request) {
        return toResponse(authService.refresh(request));
    }

    private ResponseEntity<?> toResponse(TokenDtoResponse response) {
        return ResponseEntity.ok()
                             .headers(getHeaders(response.accessToken()))
                             .body(response);
    }

    @GetMapping("/profile")
    public PersonalInfo getProfile(Principal principal) {

        final var user = userService.findByUsername(principal.getName());

        return personalInfoService.getByUserId(user.getId());
    }

    private HttpHeaders getHeaders(String token) {
        final var httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);

        return httpHeaders;
    }
}
