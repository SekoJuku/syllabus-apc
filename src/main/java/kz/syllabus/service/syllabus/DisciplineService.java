package kz.syllabus.service.syllabus;

import kz.syllabus.entity.Discipline;
import kz.syllabus.entity.syllabus.Syllabus;
import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.repository.syllabus.DisciplineRepository;
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
