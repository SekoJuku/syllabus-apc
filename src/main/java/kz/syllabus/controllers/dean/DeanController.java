package kz.syllabus.controllers.dean;

import kz.syllabus.dto.requestDto.GetUserDataDtoRequest;
import kz.syllabus.service.SyllabusService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log
@RequestMapping("/api/dean")
public class DeanController {
    private final SyllabusService syllabusService;


    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestBody GetUserDataDtoRequest request) {
        return syllabusService.getSyllabusIsSentToDean(request.getUserId());
    }
}
