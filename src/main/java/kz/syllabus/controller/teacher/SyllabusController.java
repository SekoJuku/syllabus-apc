package kz.syllabus.controller.teacher;

import kz.syllabus.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.dto.response.syllabus.SyllabusDtoResponse;
import kz.syllabus.persistence.model.Discipline;
import kz.syllabus.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.service.syllabus.SyllabusParamService;
import kz.syllabus.service.syllabus.SyllabusService;
import kz.syllabus.util.UserUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/syllabus")
@AllArgsConstructor
public class SyllabusController {

    private final SyllabusService syllabusService;
    private final SyllabusParamService syllabusParamService;
    private final UserUtil userUtil;
    private final ConversionService conversionService;

    @GetMapping
    public List<MainPageDtoResponse> getAll(Principal principal, @RequestParam Long disciplineId, @RequestParam String year) {
        return syllabusService.getSyllabusesByDisciplineAndYear(
                                      userUtil.loadUser(principal).getId(), disciplineId, year).stream()
                              .map(x -> conversionService.convert(x, MainPageDtoResponse.class))
                              .toList();
    }

    @PostMapping
    public SyllabusDtoResponse createSyllabus(@RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return conversionService.convert(syllabusService.create(fullSyllabusDTORequest, false), SyllabusDtoResponse.class);
    }

    @GetMapping("/{id}")
    public SyllabusDtoResponse getSyllabus(@PathVariable Long id, Principal principal) {

        return conversionService.convert(syllabusService.getSyllabus(userUtil.loadUser(principal).getId(), id), SyllabusDtoResponse.class);
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
        return syllabusService.getDisciplines(userUtil.loadUser(principal).getId());
    }

    @PostMapping("/approve/{id}")
    public SyllabusParam sendToCoordinator(@PathVariable Long id) {
        return syllabusParamService.sendToCoordinator(id);
    }

    @SneakyThrows
    @PostMapping("/test")
    public Discipline testCreateSyllabus(@RequestBody FullSyllabusDTORequest request) {
        return syllabusService.testCreateSyllabus(request);
    }
}
