package kz.syllabus.controller.testUser;

import kz.syllabus.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.dto.request.user.TestUserDtoRequest;
import kz.syllabus.service.syllabus.SyllabusService;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestUserController {
    private final SyllabusService syllabusService;

    @SneakyThrows
    @PostMapping("/syllabus/create")
    public ResponseEntity<?> createSyllabus(
            @RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return ResponseEntity.ok(syllabusService.create(fullSyllabusDTORequest, true));
    }

    @SneakyThrows
    @PostMapping("/syllabus")
    public ResponseEntity<?> seeAll(@RequestBody TestUserDtoRequest request) {
        return ResponseEntity.ok(syllabusService.getAllTestSyllabusesByIIN(request.getIin()));
    }
}
