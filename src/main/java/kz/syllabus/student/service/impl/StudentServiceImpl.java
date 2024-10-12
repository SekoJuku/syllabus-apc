package kz.syllabus.student.service.impl;

import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.common.persistence.repository.SyllabusRepository;
import kz.syllabus.common.service.syllabus.SyllabusService;
import kz.syllabus.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final SyllabusService syllabusService;
    private final SyllabusRepository syllabusRepository;

    @Override
    public List<Syllabus> findAll() {

        return Optional.of(syllabusRepository.findAll())
                       .map(syllabusService::checkForInstructors)
                       .map(syllabusService::checkForParam)
                       .stream()
                       .flatMap(List::stream)
                       .filter(s -> s.getSyllabusParam() != null && s.getSyllabusParam().getIsActive())
                       .toList();
    }

}
