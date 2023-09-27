package kz.syllabus.controller.testUser;

import kz.syllabus.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.dto.request.user.TestUserDtoRequest;
import kz.syllabus.service.syllabus.SyllabusService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestUserController {
    private final SyllabusService syllabusService;

    @PostMapping
    public ResponseEntity<?> createSyllabus(
            @RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return ResponseEntity.ok(syllabusService.create(fullSyllabusDTORequest, true));
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestBody TestUserDtoRequest request) {
        return ResponseEntity.ok(syllabusService.getAllTestSyllabusesByIin(request.getIin()));
    }

}
