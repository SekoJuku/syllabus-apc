package kz.syllabus.student.controller;

import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping
    public List<Syllabus> getAll() {
        return service.findAll();
    }

}
