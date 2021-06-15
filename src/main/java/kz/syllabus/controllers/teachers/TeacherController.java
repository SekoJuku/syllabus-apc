package kz.syllabus.controllers.teachers;


import kz.syllabus.dto.requestDto.GetUserDataDtoRequest;
import kz.syllabus.dto.requestDto.SyllabusDTORequest;
import kz.syllabus.dto.requestDto.GetSyllabusDtoRequest;
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
    public ResponseEntity<?> getSyllabus(@RequestBody GetSyllabusDtoRequest getSyllabusDtoRequest) {
        return syllabusService.getOne(getSyllabusDtoRequest.getUserId(), getSyllabusDtoRequest.getDisciplineInfoId());
    }
    @PostMapping("/data")
    public ResponseEntity<?> getData(@RequestBody GetUserDataDtoRequest getUserDataDtoRequest) {
        return syllabusService.getUserData(getUserDataDtoRequest.getUserId());
    }
}
