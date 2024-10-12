package kz.syllabus.dean.controller;

import kz.syllabus.common.converter.SyllabusDtoConverter;
import kz.syllabus.common.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.common.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.dean.service.DeanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dean")
@AllArgsConstructor
public class DeanController {
    private final DeanService deanService;
    private final SyllabusDtoConverter converter;

    @PostMapping
    public List<MainPageDtoResponse> getAll() {
        return deanService.getAll().stream()
                          .map(converter::convertToMainPage)
                          .toList();
    }

    @GetMapping("/approve/{id}")
    public SyllabusParam approve(@PathVariable Long id) {
        return deanService.approve(id);
    }

}
