package kz.syllabus.common.service.syllabus;

import kz.syllabus.common.persistence.model.Discipline;
import kz.syllabus.common.persistence.model.Prerequisite;
import kz.syllabus.common.persistence.repository.PrerequisiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@RequiredArgsConstructor
public class PrerequisiteService {
    private final PrerequisiteRepository repository;

    public List<Prerequisite> getAllBySyllabusId(Long syllabusId) {
        return repository.getAllBySyllabusId(syllabusId);
    }

    public void createAll(List<Long> ids, Discipline discipline, Long syllabusId) {
        ids.stream()
           .filter(item ->
                           !repository.existsByDisciplineIdAndSyllabusId(
                                   discipline.getId(), syllabusId))
           .forEach(item -> {
               final var prerequisite = new Prerequisite();
               prerequisite.setDisciplineId(discipline.getId());
               prerequisite.setSyllabusId(syllabusId);
               repository.save(prerequisite);
           });
    }
}
