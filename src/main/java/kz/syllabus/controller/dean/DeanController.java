package kz.syllabus.controller.dean;

import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.entity.syllabus.SyllabusParam;
import kz.syllabus.service.DeanService;
import kz.syllabus.util.SyllabusUtil;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/dean")
@AllArgsConstructor
public class DeanController {
    private final DeanService  deanService;
    private final SyllabusUtil syllabusUtil;

    @PostMapping
    public List<MainPageDtoResponse> getAll() {
        return syllabusUtil.toMainPageDtoResponse(deanService.getAll());
    }

    @GetMapping("/approve/{id}")
    public SyllabusParam approve(@PathVariable Long id) {
        return deanService.approve(id);
    }

}
