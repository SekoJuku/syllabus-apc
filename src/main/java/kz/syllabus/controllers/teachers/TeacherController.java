package kz.syllabus.controllers.teachers;


import kz.syllabus.dto.requestDto.GetUserDataDtoRequest;
import kz.syllabus.dto.requestDto.FullSyllabusDTORequest;
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
    public ResponseEntity<?> getAll(@RequestBody GetSyllabusDtoRequest request) {
        return syllabusService.getAll(request.getUserId());
    }

    @PostMapping("/syllabus/create")
    public ResponseEntity<?> createSyllabus(@RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return syllabusService.create(fullSyllabusDTORequest);
    }
    @PostMapping("/syllabus")
    public ResponseEntity<?> getSyllabus(@RequestBody GetSyllabusDtoRequest getSyllabusDtoRequest) {
        return syllabusService.getOne(getSyllabusDtoRequest.getUserId(), getSyllabusDtoRequest.getSyllabusId());
    }

    @GetMapping("/syllabus/{id}")
    public ResponseEntity<?> getSyllabusById(@PathVariable Integer id){
        return ResponseEntity.ok(syllabusService.getSyllabusById(id));
    }

//    @PostMapping("/data")
//    public ResponseEntity<?> getData(@RequestBody GetUserDataDtoRequest getUserDataDtoRequest) {
//        return syllabusService.getUserData(getUserDataDtoRequest.getUserId());
//    }
}
