package kz.syllabus.coordinator.controller;

import kz.syllabus.common.converter.SyllabusDtoConverter;
import kz.syllabus.common.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.common.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.common.service.user.UserService;
import kz.syllabus.coordinator.service.CoordinatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/coordinator")
@AllArgsConstructor
public class CoordinatorController {

    private final CoordinatorService service;
    private final UserService userService;
    private final SyllabusDtoConverter converter;

    @PostMapping
    public List<MainPageDtoResponse> getAll(Principal principal) {
        return service.getSyllabuses(userService.findByUsername(principal.getName())).stream()
                      .map(converter::convertToMainPage)
                      .toList();
    }

    @GetMapping("/approve/{id}")
    public SyllabusParam approvedSyllabusById(@PathVariable Long id) {
        return service.approve(id);
    }

    @PostMapping("/test")
    public List<MainPageDtoResponse> getAllTest(Principal principal) {
        return service.getTestSyllabuses(userService.findByUsername(principal.getName())).stream()
                      .map(converter::convertToMainPage)
                      .toList();
    }

}
