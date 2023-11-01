package kz.syllabus.controller.dean;

import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.service.DeanService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/dean")
@AllArgsConstructor
public class DeanController {
    private final DeanService deanService;
    private final ConversionService conversionService;

    @PostMapping
    public List<MainPageDtoResponse> getAll() {
        return deanService.getAll().stream()
                          .map(x -> conversionService.convert(x, MainPageDtoResponse.class))
                          .toList();
    }

    @GetMapping("/approve/{id}")
    public SyllabusParam approve(@PathVariable Long id) {
        return deanService.approve(id);
    }

}
