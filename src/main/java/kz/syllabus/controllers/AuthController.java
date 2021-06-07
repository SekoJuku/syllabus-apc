package kz.syllabus.controllers;


import kz.syllabus.dto.requestDto.UserDtoRequest;
import kz.syllabus.exceptions.ExceptionHandling;
import kz.syllabus.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log
public class AuthController extends ExceptionHandling {
    @Autowired
    private final UserService userService;


    @PostMapping("/api/auth")
    public ResponseEntity<?> auth(@RequestBody UserDtoRequest userDtoRequest){
        log.info(userDtoRequest.getUsername() + "---" + userDtoRequest.getPassword());
        return userService.auth(userDtoRequest.getUsername(),userDtoRequest.getPassword());
    }


}
