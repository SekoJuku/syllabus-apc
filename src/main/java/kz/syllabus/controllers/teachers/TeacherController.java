package kz.syllabus.controllers.teachers;


import kz.syllabus.dto.requestDto.SyllabusDTORequest;
import kz.syllabus.exceptions.ExceptionHandling;
import kz.syllabus.service.SyllabusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController extends ExceptionHandling {
    private final SyllabusService syllabusService;

    @PostMapping("")
    public ResponseEntity<?> getAll(@RequestParam("userId") Integer userId) {
        return syllabusService.getAll(userId);
    }

    @PostMapping("/syllabus/create")
    public ResponseEntity<?> createSyllabus(@RequestBody SyllabusDTORequest syllabusDTORequest) {
        return syllabusService.create(syllabusDTORequest);
    }
    @PostMapping("/syllabus")
    public ResponseEntity<?> getSyllabus(@RequestParam("userId") Integer userId, @RequestParam("disciplineInfoId") Integer disciplineInfoId) {
        return syllabusService.getOne(userId, disciplineInfoId);
    }
    @PostMapping("/data")
    public ResponseEntity<?> getData(@RequestParam("userId") Integer userId) {
        return syllabusService.getUserData(userId);
    }
}
