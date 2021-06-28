package kz.syllabus.controllers.teachers;


import kz.syllabus.dto.requestDto.GetSyllabusesByDiscipleAndYearDtoRequest;
import kz.syllabus.dto.requestDto.GetUserDataDtoRequest;
import kz.syllabus.dto.requestDto.FullSyllabusDTORequest;
import kz.syllabus.dto.requestDto.GetSyllabusDtoRequest;
import kz.syllabus.exceptions.ExceptionHandling;
import kz.syllabus.service.SyllabusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


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
        return syllabusService.create(fullSyllabusDTORequest, true);
    }
    @PostMapping("/syllabus")
    public ResponseEntity<?> getSyllabus(@RequestBody GetSyllabusDtoRequest getSyllabusDtoRequest) {
        return syllabusService.getSyllabus(getSyllabusDtoRequest.getUserId(), getSyllabusDtoRequest.getSyllabusId());
    }

    @GetMapping("/syllabus/{id}")
    public ResponseEntity<?> getSyllabusById(@PathVariable Integer id){
        return ResponseEntity.ok(syllabusService.getSyllabusById(id));
    }

    @GetMapping("/syllabus/delete/{id}")
    public ResponseEntity<?> deleteSyllabusById(@PathVariable Integer id) {
        return syllabusService.deleteSyllabusById(id);
    }
    @GetMapping("/checkFinal/{id}")
    public ResponseEntity<?> checkFinal(@PathVariable Integer id) {
        return syllabusService.checkForFinal(id);
    }

    @PostMapping("/data")
    public ResponseEntity<?> getData(@RequestBody GetUserDataDtoRequest getUserDataDtoRequest) {
        return syllabusService.getUserData(getUserDataDtoRequest.getUserId());
    }
    @PostMapping("/disciplines")
    public ResponseEntity<?> getDisciplines(@RequestBody GetUserDataDtoRequest request) {
        return syllabusService.getDisciplines(request.getUserId());
    }
    @PostMapping("/syllabus/discipline/year")
    public ResponseEntity<?> getSyllabusesByDiscipleAndYear(@RequestBody GetSyllabusesByDiscipleAndYearDtoRequest request) {
        return syllabusService.getSyllabusesByDiscipleAndYear(request.getUserId(),request.getDisciplineId(), request.getYear());
    }
    @PostMapping("/sendToCoordinator")
    public ResponseEntity<?> sendToCoordinator(@RequestBody GetSyllabusDtoRequest request) {
        return syllabusService.sendToCoordinator(request.getSyllabusId());
    }
    @PostMapping("/test/syllabus/create")
    public ResponseEntity<?> testCreateSyllabus(@RequestBody FullSyllabusDTORequest request) {
        return syllabusService.testCreateSyllabus(request);
    }
    @GetMapping("/test/syllabus/{id}")
    public ResponseEntity<?> getFullSyllabus(@PathVariable Integer id) {
        return syllabusService.getFullSyllabus(id);
    }
}
