package kz.syllabus.controller.testUser;

import kz.syllabus.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.dto.request.user.TestUserDtoRequest;
import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.dto.response.syllabus.SyllabusDtoResponse;
import kz.syllabus.service.syllabus.SyllabusService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestUserController {
    private final SyllabusService syllabusService;
    private final ConversionService conversionService;

    @PostMapping
    public SyllabusDtoResponse createSyllabus(
            @RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return conversionService.convert(syllabusService.create(fullSyllabusDTORequest, true), SyllabusDtoResponse.class);
    }

    @GetMapping
    public List<MainPageDtoResponse> getAll(@RequestBody TestUserDtoRequest request) {
        var response = syllabusService.getAllTestSyllabusesByIin(request.getIin());
        return response.stream()
                       .map(x -> conversionService.convert(x, MainPageDtoResponse.class))
                       .toList();
    }

}
