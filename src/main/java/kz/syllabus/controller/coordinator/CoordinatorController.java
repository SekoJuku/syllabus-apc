package kz.syllabus.controller.coordinator;

import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.entity.syllabus.SyllabusParam;
import kz.syllabus.service.CoordinatorService;
import kz.syllabus.util.SyllabusUtil;
import kz.syllabus.util.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Log
@RestController
@RequestMapping("/coordinator")
@AllArgsConstructor
public class CoordinatorController {
    private final CoordinatorService service;
    private final UserUtils          userUtils;
    private final SyllabusUtil       syllabusUtil;

    @PostMapping
    public List<MainPageDtoResponse> getAll(Principal principal) {
        return syllabusUtil.toMainPageDtoResponse(service.getSyllabuses(userUtils.loadUser(principal)));
    }

    @GetMapping("/approve/{id}")
    public SyllabusParam approvedSyllabusById(@PathVariable Long id) {
        return service.approve(id);
    }

    @PostMapping("/test")
    public List<MainPageDtoResponse> getAllTest(Principal principal) {
        return syllabusUtil.toMainPageDtoResponse(service.getTestSyllabuses(userUtils.loadUser(principal)));
    }

}
