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

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestBody GetUserDataDtoRequest request) {
        return syllabusService.getSyllabusIsSentToCoordinator(request.getUserId());
    }
    @GetMapping("/{id}/approved")
    public ResponseEntity<?> approvedSyllabusById(@PathVariable Integer id) {
        return syllabusService.approvedSyllabusById(id);
    }
}
