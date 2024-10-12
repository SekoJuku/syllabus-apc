package kz.syllabus.student.controller;

import kz.syllabus.common.converter.SyllabusDtoConverter;
import kz.syllabus.common.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.common.dto.request.user.TestUserDtoRequest;
import kz.syllabus.common.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.common.dto.response.syllabus.SyllabusDtoResponse;
import kz.syllabus.common.service.syllabus.SyllabusService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestUserController {
    private final SyllabusService syllabusService;
    private final SyllabusDtoConverter converter;

    @PostMapping
    public SyllabusDtoResponse createSyllabus(@RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return converter.convertToSyllabus(syllabusService.create(fullSyllabusDTORequest, true));
    }

    @GetMapping
    public List<MainPageDtoResponse> getAll(@RequestBody TestUserDtoRequest request) {
        var response = syllabusService.getAllTestSyllabusesByIin(request.getIin());
        return response.stream()
                       .map(converter::convertToMainPage)
                       .toList();
    }

}
