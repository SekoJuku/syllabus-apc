package kz.syllabus.controllers.testUser;

import kz.syllabus.dto.requestDto.FullSyllabusDTORequest;
import kz.syllabus.dto.requestDto.TestUserDtoRequest;
import kz.syllabus.service.SyllabusService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log
@RequestMapping("/api/testUser")
public class TestUserController {
    private final SyllabusService syllabusService;

    @PostMapping("/syllabus/create")
    public ResponseEntity<?> createSyllabus(@RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return ResponseEntity.ok(syllabusService.create(fullSyllabusDTORequest, true));
    }
    @PostMapping("/syllabus")
    public ResponseEntity<?> seeAll(@RequestBody TestUserDtoRequest request) {
        return ResponseEntity.ok(syllabusService.getAllTestSyllabusesByIIN(request.getIin()));
    }
}
