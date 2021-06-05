package kz.syllabus.controller;


import kz.syllabus.dto.requestDto.UserDtoRequest;
import kz.syllabus.dto.responseDto.UserDtoResponse;
import kz.syllabus.entity.security.VerificationToken;
import kz.syllabus.repository.VerificationTokenRepository;
import kz.syllabus.service.PasswordService;
import kz.syllabus.service.UserService;
import kz.syllabus.service.UsernameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class AuthController {

    private final UserService userService;
    private final UsernameService usernameService;


    @PostMapping("/login")
    public ResponseEntity<UserDtoResponse> login(@RequestBody UserDtoRequest userDtoRequest, HttpServletRequest request) {
        return userService.authorization(userDtoRequest.getUsername(), userDtoRequest.getPassword(), request);
    }

    @GetMapping("/user-verification")
    public ResponseEntity<HttpStatus> userVerification(@RequestParam("token") String token) {
        VerificationToken verificationToken = usernameService.getVerificationTokenByToken(token);
        User user = verificationToken.getUser();
        userService.verifyUser(user);
        return new ResponseEntity<>(OK);
    }

    public VerificationToken getVerificationTokenByToken(String token) {
        List<VerificationToken> verificationTokenCheck = verificationTokenRepository.findAll();
        for (VerificationToken verificationToken : verificationTokenCheck) {
            if (verificationToken.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + 7200000 <= System.currentTimeMillis()) {
                verificationTokenRepository.delete(verificationToken);
            }
        }
    }
}
