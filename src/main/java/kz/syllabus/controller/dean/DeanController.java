package kz.syllabus.controller.dean;

import kz.syllabus.dto.request.user.GetUserDataDtoRequest;
import kz.syllabus.service.syllabus.SyllabusService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@RequestMapping("/dean")
@AllArgsConstructor
public class DeanController {
    private final SyllabusService syllabusService;

    @PostMapping("")
    public ResponseEntity<?> getAll(@RequestBody GetUserDataDtoRequest request) {
        return ResponseEntity.ok(syllabusService.getSyllabusIsSentToDean(request.getUserId()));
    }

    @GetMapping("/{id}/approved")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        return ResponseEntity.ok(syllabusService.approvedByDeanById(id));
    }
}
