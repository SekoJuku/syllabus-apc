package kz.syllabus.controller.coordinator;

import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.service.CoordinatorService;
import kz.syllabus.util.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Log
@RestController
@RequestMapping("/coordinator")
@AllArgsConstructor
public class CoordinatorController {
    private final CoordinatorService service;
    private final UserUtils userUtils;
    private final ConversionService conversionService;

    @PostMapping
    public List<MainPageDtoResponse> getAll(Principal principal) {
        return service.getSyllabuses(userUtils.loadUser(principal)).stream()
                      .map(x -> conversionService.convert(x, MainPageDtoResponse.class))
                      .toList();
    }

    @GetMapping("/approve/{id}")
    public SyllabusParam approvedSyllabusById(@PathVariable Long id) {
        return service.approve(id);
    }

    @PostMapping("/test")
    public List<MainPageDtoResponse> getAllTest(Principal principal) {
        return service.getTestSyllabuses(userUtils.loadUser(principal)).stream()
                      .map(x -> conversionService.convert(x, MainPageDtoResponse.class))
                      .toList();
    }

}
