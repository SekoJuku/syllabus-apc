package kz.syllabus.controllers.admin;

import kz.syllabus.entity.PersonalInfo;
import kz.syllabus.entity.User;
import kz.syllabus.service.PersonalInfoService;
import kz.syllabus.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdminController {
    private final UserService userService;
    private final PersonalInfoService personalInfoService;

    @PostMapping("/admin/register/teacher")
    public void registerNewTeacher(@RequestBody PersonalInfo personalInfo){
        personalInfoService.registerNewTeacher(personalInfo);
        userService.registerNewTeacher(personalInfo);
    }
}
