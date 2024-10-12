package kz.syllabus.teacher.controller;

import kz.syllabus.common.converter.SyllabusDtoConverter;
import kz.syllabus.common.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.common.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.common.dto.response.syllabus.SyllabusDtoResponse;
import kz.syllabus.common.persistence.model.Discipline;
import kz.syllabus.common.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.common.service.syllabus.SyllabusParamService;
import kz.syllabus.common.service.syllabus.SyllabusService;
import kz.syllabus.common.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/syllabus")
@AllArgsConstructor
public class SyllabusController {

    private final SyllabusService syllabusService;
    private final SyllabusParamService syllabusParamService;
    private final UserService userService;
    private final SyllabusDtoConverter converter;

    @GetMapping
    public List<MainPageDtoResponse> getAll(Principal principal, @RequestParam Long disciplineId, @RequestParam String year) {

        final var user = userService.findByUsername(principal.getName());

        return syllabusService.getSyllabusesByDisciplineAndYear(disciplineId, year)
                              .stream()
                              .map(converter::convertToMainPage)
                              .toList();
    }

    @PostMapping
    public SyllabusDtoResponse createSyllabus(@RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {

        final var syllabus = syllabusService.create(fullSyllabusDTORequest, false);

        return converter.convertToSyllabus(syllabus);
    }

    @GetMapping("/{id}")
    public SyllabusDtoResponse getSyllabus(@PathVariable Long id, Principal principal) {

        final var user = userService.findByUsername(principal.getName());

        final var syllabus = syllabusService.findById(id);

        return converter.convertToSyllabus(syllabus);
    }

    @DeleteMapping("/{id}")
    public void deleteSyllabusById(@PathVariable Long id) {
        syllabusService.deleteById(id);
    }

    @GetMapping("/isFinal/{id}")
    public Boolean checkFinal(@PathVariable Long id) {
        return syllabusService.checkForFinal(id);
    }

    @GetMapping("/disciplines")
    public List<Discipline> getDisciplines(Principal principal) {

        final var user = userService.findByUsername(principal.getName());

        return syllabusService.getDisciplines();
    }

    @PostMapping("/approve/{id}")
    public SyllabusParam sendToCoordinator(@PathVariable Long id) {
        return syllabusParamService.sendToCoordinator(id);
    }

    @PostMapping("/test")
    public Discipline testCreateSyllabus(@RequestBody FullSyllabusDTORequest request) {
        return syllabusService.testCreateSyllabus(request);
    }

}
