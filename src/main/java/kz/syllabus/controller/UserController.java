package kz.syllabus.controller;

import kz.syllabus.persistence.model.PersonalInfo;
import kz.syllabus.service.user.PersonalInfoService;
import kz.syllabus.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Log
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final PersonalInfoService personalInfoService;
    private final UserUtil userUtil;

    @GetMapping("/profile")
    public PersonalInfo getProfile(Principal principal) {
        return personalInfoService.getByUserId(userUtil.loadUser(principal).getId());
    }

}
