package kz.syllabus.controller.coordinator;

import kz.syllabus.dto.request.user.GetUserDataDtoRequest;
import kz.syllabus.service.syllabus.SyllabusService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@RequestMapping("/coordinator")
@AllArgsConstructor
public class CoordinatorController {
    private final SyllabusService syllabusService;

    @PostMapping("")
    public ResponseEntity<?> getAll(@RequestBody GetUserDataDtoRequest request) {
        return ResponseEntity.ok(
                syllabusService.getSyllabusIsSentToCoordinator(request.getUserId()));
    }

    @GetMapping("/{id}/approved")
    public ResponseEntity<?> approvedSyllabusById(@PathVariable Long id) {
        return ResponseEntity.ok(syllabusService.approvedSyllabusById(id));
    }

    @PostMapping("/getAllTest")
    public ResponseEntity<?> getAllTest(@RequestBody GetUserDataDtoRequest request) {
        return ResponseEntity.ok(syllabusService.getAllTest(request.getUserId()));
    }
}
