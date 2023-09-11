package kz.syllabus.service.syllabus;

import kz.syllabus.entity.Discipline;
import kz.syllabus.entity.Postrequisite;
import kz.syllabus.repository.syllabus.PostrequisiteRepository;
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

    public List<Postrequisite> createAll(List<Long> ids, Discipline discipline, Long id) {
        return ids.stream()
                .filter(
                        item ->
                                !repository.existsByDisciplineIdAndSyllabusId(
                                        discipline.getId(), id))
                .map(
                        item -> {
                            Postrequisite postrequisite = new Postrequisite();
                            postrequisite.setDisciplineId(discipline.getId());
                            postrequisite.setSyllabusId(id);
                            return repository.save(postrequisite);
                        })
                .toList();
    }
}
