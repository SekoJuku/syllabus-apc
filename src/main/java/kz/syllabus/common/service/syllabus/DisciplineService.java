package kz.syllabus.common.service.syllabus;

import kz.syllabus.common.exception.domain.NotFoundException;
import kz.syllabus.common.persistence.model.Discipline;
import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.common.persistence.repository.DisciplineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;

    public Discipline getById(Long id) throws NotFoundException {
        return disciplineRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Discipline not found"));
    }

    public Discipline addSyllabus(Discipline discipline, Syllabus syllabus) {
        discipline.getSyllabuses().add(syllabus);
        return disciplineRepository.save(discipline);
    }

    public List<Discipline> getAll() {
        return disciplineRepository.findAll();
    }
}
