package kz.syllabus.controller;

import kz.syllabus.dto.request.user.LoginDtoRequest;
import kz.syllabus.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody LoginDtoRequest loginDtoRequest) {
        return authService.auth(loginDtoRequest);
    }
}
