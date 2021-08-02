package kz.syllabus.controllers.coordinator;

import kz.syllabus.dto.requestDto.GetUserDataDtoRequest;
import kz.syllabus.service.SyllabusService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Log
@RequestMapping("/api/coordinator")
public class CoordinatorController {
    private final SyllabusService syllabusService;

    @PostMapping("")
    public ResponseEntity<?> getAll(@RequestBody GetUserDataDtoRequest request) {
        return ResponseEntity.ok(syllabusService.getSyllabusIsSentToCoordinator(request.getUserId()));
    }
    @GetMapping("/{id}/approved")
    public ResponseEntity<?> approvedSyllabusById(@PathVariable Integer id) {
        return ResponseEntity.ok(syllabusService.approvedSyllabusById(id));
    }
    @PostMapping("/getAllTest")
    public ResponseEntity<?> getAllTest(@RequestBody GetUserDataDtoRequest request) {
        return ResponseEntity.ok(syllabusService.getAllTest(request.getUserId()));
    }
}
