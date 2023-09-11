package kz.syllabus.controller.teacher;

import kz.syllabus.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.dto.response.syllabus.FullSyllabusDtoResponse;
import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.entity.Discipline;
import kz.syllabus.entity.syllabus.SyllabusParam;
import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.service.syllabus.SyllabusParamService;
import kz.syllabus.service.syllabus.SyllabusService;
import kz.syllabus.util.UserUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/syllabus")
@AllArgsConstructor
public class SyllabusController {
    private final SyllabusService      syllabusService;
    private final SyllabusParamService syllabusParamService;
    private final UserUtils            userUtils;

    @GetMapping
    public List<MainPageDtoResponse> getAll(Principal principal, @RequestParam Long disciplineId, @RequestParam String year) {
        return syllabusService.getSyllabusesByDisciplineAndYear(
                userUtils.loadUser(principal).getId(), disciplineId, year);
    }

    @PostMapping
    public FullSyllabusDtoResponse createSyllabus(@RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return syllabusService.create(fullSyllabusDTORequest, false);
    }

    @GetMapping("/{id}")
    public FullSyllabusDtoResponse getSyllabus(@PathVariable Long id, Principal principal) {

        return syllabusService.getSyllabus(userUtils.loadUser(principal).getId(), id);
    }

    @DeleteMapping("/{id}")
    public void deleteSyllabusById(@PathVariable Long id) throws NotFoundException {
        syllabusService.deleteById(id);
    }

    @GetMapping("/checkFinal/{id}")
    public Boolean checkFinal(@PathVariable Long id) {
        return syllabusService.checkForFinal(id);
    }

    @PostMapping("/disciplines")
    public List<Discipline> getDisciplines(Principal principal) {
        return syllabusService.getDisciplines(userUtils.loadUser(principal).getId());
    }

    @PostMapping("/approve/{id}")
    public SyllabusParam sendToCoordinator(@PathVariable Long id) {
        return syllabusParamService.setSentToCoordinator(id);
    }

    @SneakyThrows
    @PostMapping("/test/syllabus/create")
    public Discipline testCreateSyllabus(@RequestBody FullSyllabusDTORequest request) {
        return syllabusService.testCreateSyllabus(request);
    }
}
