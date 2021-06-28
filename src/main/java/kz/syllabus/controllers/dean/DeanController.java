package kz.syllabus.controllers.dean;

import kz.syllabus.dto.requestDto.GetUserDataDtoRequest;
import kz.syllabus.service.SyllabusService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Log
@RequestMapping("/api/dean")
public class DeanController {
    private final SyllabusService syllabusService;


    @PostMapping("")
    public ResponseEntity<?> getAll(@RequestBody GetUserDataDtoRequest request) {
        return syllabusService.getSyllabusIsSentToDean(request.getUserId());
    }

    @GetMapping("/{id}/approved")
    public ResponseEntity<?> approve(@PathVariable Integer id) {
        return syllabusService.approvedByDeanById(id);
    }
}
