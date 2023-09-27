package kz.syllabus.service;

import kz.syllabus.persistence.model.syllabus.Syllabus;
import kz.syllabus.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.service.syllabus.SyllabusParamService;
import kz.syllabus.service.syllabus.SyllabusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeanService {
    private final SyllabusService syllabusService;
    private final SyllabusParamService syllabusParamService;

    public List<Syllabus> getAll() {
        return syllabusParamService.getAllSentToDean().stream()
                .map(SyllabusParam::getSyllabus)
                .toList();

    }

    public SyllabusParam approve(Long id) {
        Syllabus syllabus = syllabusService.getById(id);
        syllabus.getSyllabusParam().setIsApprovedByDean(true);
        syllabus.getSyllabusParam().setIsActive(true);
        return syllabusService.save(syllabus).getSyllabusParam();

    }
}
