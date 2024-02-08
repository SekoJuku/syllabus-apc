package kz.syllabus.service.syllabus;

import kz.syllabus.persistence.model.Discipline;
import kz.syllabus.persistence.model.Postrequisite;
import kz.syllabus.persistence.repository.PostrequisiteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@AllArgsConstructor
public class PostrequisiteService {
    private final PostrequisiteRepository repository;

    public List<Postrequisite> getAllBySyllabusId(Long syllabusId) {
        return repository.getAllBySyllabusId(syllabusId);
    }

    public void createAll(List<Long> ids, Discipline discipline, Long id) {
        ids.stream()
           .filter(item ->
                   !repository.existsByDisciplineIdAndSyllabusId(
                           discipline.getId(), id))
           .forEach(item -> {
               final var postrequisite = new Postrequisite();
               postrequisite.setDisciplineId(discipline.getId());
               postrequisite.setSyllabusId(id);
               repository.save(postrequisite);
           });
    }
}
