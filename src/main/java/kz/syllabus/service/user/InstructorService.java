package kz.syllabus.service.user;

import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.persistence.model.PersonalInfo;
import kz.syllabus.persistence.model.user.Instructor;
import kz.syllabus.persistence.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final PersonalInfoService personalInfoService;

    public List<Instructor> getBySyllabusId(Long id) throws NotFoundException {
        return instructorRepository
                .findBySyllabusId(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found"));
    }

    public void deleteAllBySyllabusId(Long id) {
        instructorRepository.deleteAllBySyllabusId(id);
    }

    public boolean existsBySyllabusId(Long id) {
        return instructorRepository.existsBySyllabusId(id);
    }

    public PersonalInfo getPersonalInfo(Instructor instructor) {
        return personalInfoService.getByUserId(instructor.getUserId());
    }

}
