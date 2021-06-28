package kz.syllabus.controllers;

import kz.syllabus.service.DisciplineInfoService;
import kz.syllabus.service.SyllabusService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log
public class DisciplineController {
    private final SyllabusService syllabusService;

    @GetMapping("/syllabus/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(syllabusService.findAll());
    }
}
