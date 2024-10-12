package kz.syllabus.common.service.user;

import kz.syllabus.common.persistence.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public void deleteAllBySyllabusId(Long id) {
        instructorRepository.deleteAllBySyllabusId(id);
    }

    public boolean existsBySyllabusId(Long id) {
        return instructorRepository.existsBySyllabusId(id);
    }

}
