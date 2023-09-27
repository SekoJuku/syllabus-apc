package kz.syllabus.service;

import kz.syllabus.persistence.model.syllabus.Syllabus;
import kz.syllabus.service.syllabus.SyllabusParamService;
import kz.syllabus.service.syllabus.SyllabusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final SyllabusService syllabusService;
    private final SyllabusParamService syllabusParamService;

    public List<Syllabus> findAll() {

        return Optional.of(syllabusService.findAll())
                .map(syllabusService::checkForInstructors)
                .map(syllabusService::checkForParam)
                .stream()
                .flatMap(List::stream)
                .filter(item -> syllabusParamService.activeBySyllabusId(item.getId()))
                .toList();
    }
}
