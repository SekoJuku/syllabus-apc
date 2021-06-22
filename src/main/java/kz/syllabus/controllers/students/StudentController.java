package kz.syllabus.controllers.students;

import kz.syllabus.service.SyllabusService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log
@RequestMapping("/api/student")
public class StudentController {
    private final SyllabusService syllabusService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return syllabusService.getAllFull();
    }

}
