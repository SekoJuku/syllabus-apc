package kz.syllabus.controllers.teachers;


import kz.syllabus.dto.requestDto.GetSyllabusesByDiscipleAndYearDtoRequest;
import kz.syllabus.dto.requestDto.GetUserDataDtoRequest;
import kz.syllabus.dto.requestDto.FullSyllabusDTORequest;
import kz.syllabus.dto.requestDto.GetSyllabusDtoRequest;
import kz.syllabus.entity.Syllabus;
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
        return ResponseEntity.ok(syllabusService.getAll(request.getUserId()));
    }

    @PostMapping("/syllabus/create")
    public ResponseEntity<?> createSyllabus(@RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return ResponseEntity.ok(syllabusService.create(fullSyllabusDTORequest, false));
    }
    @PostMapping("/syllabus")
    public ResponseEntity<?> getSyllabus(@RequestBody GetSyllabusDtoRequest getSyllabusDtoRequest) {
        return ResponseEntity.ok(syllabusService.getSyllabus(getSyllabusDtoRequest.getUserId(), getSyllabusDtoRequest.getSyllabusId()));
    }

    @GetMapping("/syllabus/{id}")
    public ResponseEntity<?> getSyllabusById(@PathVariable Integer id){
        return ResponseEntity.ok(syllabusService.getSyllabusById(id));
    }

    @GetMapping("/syllabus/delete/{id}")
    public ResponseEntity<?> deleteSyllabusById(@PathVariable Integer id) {
        return ResponseEntity.ok(syllabusService.deleteSyllabusById(id));
    }
    @GetMapping("/checkFinal/{id}")
    public ResponseEntity<?> checkFinal(@PathVariable Integer id) {
        return ResponseEntity.ok(syllabusService.checkForFinal(id));
    }

    @PostMapping("/data")
    public ResponseEntity<?> getData(@RequestBody GetUserDataDtoRequest getUserDataDtoRequest) {
        return ResponseEntity.ok(syllabusService.getUserData(getUserDataDtoRequest.getUserId()));
    }
    @PostMapping("/disciplines")
    public ResponseEntity<?> getDisciplines(@RequestBody GetUserDataDtoRequest request) {
        return ResponseEntity.ok(syllabusService.getDisciplines(request.getUserId()));
    }
    @PostMapping("/syllabus/discipline/year")
    public ResponseEntity<?> getSyllabusesByDiscipleAndYear(@RequestBody GetSyllabusesByDiscipleAndYearDtoRequest request) {
        return ResponseEntity.ok(syllabusService.getSyllabusesByDiscipleAndYear(request.getUserId(),request.getDisciplineId(), request.getYear()));
    }
    @PostMapping("/sendToCoordinator")
    public ResponseEntity<?> sendToCoordinator(@RequestBody GetSyllabusDtoRequest request) {
        return ResponseEntity.ok(syllabusService.sendToCoordinator(request.getSyllabusId()));
    }
    @PostMapping("/test/syllabus/create")
    public ResponseEntity<?> testCreateSyllabus(@RequestBody FullSyllabusDTORequest request) {
        return ResponseEntity.ok(syllabusService.testCreateSyllabus(request));
    }
    @GetMapping("/test/syllabus/{id}")
    public ResponseEntity<?> getFullSyllabus(@PathVariable Integer id) {
        Syllabus syllabus = syllabusService.getFullSyllabus(id);
        if(syllabus != null) {
            return ResponseEntity.ok(syllabus);
        }
        return ResponseEntity.badRequest().body("Object by this id is not found!");
    }
}
