package kz.syllabus.controller.teacher;

import kz.syllabus.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.dto.request.syllabus.GetSyllabusDtoRequest;
import kz.syllabus.dto.request.syllabus.GetSyllabusesByDiscipleAndYearDtoRequest;
import kz.syllabus.dto.request.user.GetUserDataDtoRequest;
import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.service.syllabus.SyllabusService;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/syllabus")
@AllArgsConstructor
public class SyllabusController {
    private final SyllabusService syllabusService;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam Long userId) {
        return ResponseEntity.ok(syllabusService.getAll(userId));
    }

    @SneakyThrows
    @PostMapping("")
    public ResponseEntity<?> createSyllabus(
            @RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return ResponseEntity.ok(syllabusService.create(fullSyllabusDTORequest, false));
    }

    @SneakyThrows
    @PostMapping("/syllabus")
    public ResponseEntity<?> getSyllabus(@RequestBody GetSyllabusDtoRequest getSyllabusDtoRequest) {
        return ResponseEntity.ok(
                syllabusService.getSyllabus(
                        getSyllabusDtoRequest.getUserId(), getSyllabusDtoRequest.getSyllabusId()));
    }

    @GetMapping("/syllabus/{id}")
    public ResponseEntity<?> getSyllabusById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(syllabusService.getById(id));
    }

    @DeleteMapping("/syllabus/{id}")
    public ResponseEntity<?> deleteSyllabusById(@PathVariable Long id) throws NotFoundException {
        syllabusService.deleteById(id);
        return ResponseEntity.accepted().body("Syllabus with id " + id + " is deleted!");
    }

    @GetMapping("/checkFinal/{id}")
    public ResponseEntity<?> checkFinal(@PathVariable Long id) {
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
    public ResponseEntity<?> getSyllabusesByDiscipleAndYear(
            @RequestBody GetSyllabusesByDiscipleAndYearDtoRequest request)
            throws NotFoundException {
        return ResponseEntity.ok(
                syllabusService.getSyllabusesByDisciplineAndYear(
                        request.getUserId(), request.getDisciplineId(), request.getYear()));
    }

    @PostMapping("/sendToCoordinator")
    public ResponseEntity<?> sendToCoordinator(@RequestBody GetSyllabusDtoRequest request) {
        return ResponseEntity.ok(syllabusService.sendToCoordinator(request.getSyllabusId()));
    }

    @SneakyThrows
    @PostMapping("/test/syllabus/create")
    public ResponseEntity<?> testCreateSyllabus(@RequestBody FullSyllabusDTORequest request) {
        return ResponseEntity.ok(syllabusService.testCreateSyllabus(request));
    }
}
