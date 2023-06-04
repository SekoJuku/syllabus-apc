package kz.syllabus.service;

import kz.syllabus.entity.Discipline;
import kz.syllabus.entity.Prerequisite;
import kz.syllabus.repository.PrerequisiteRepository;

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

    public List<Prerequisite> createAll(List<Long> ids, Discipline discipline, Long syllabusId) {
        return ids.stream()
                .filter(
                        item ->
                                !repository.existsByDisciplineIdAndSyllabusId(
                                        discipline.getId(), syllabusId))
                .map(
                        item -> {
                            Prerequisite prerequisite = new Prerequisite();
                            prerequisite.setDisciplineId(discipline.getId());
                            prerequisite.setSyllabusId(syllabusId);
                            return repository.save(prerequisite);
                        })
                .toList();
    }
}
