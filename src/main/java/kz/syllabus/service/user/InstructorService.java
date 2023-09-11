package kz.syllabus.service.user;

import kz.syllabus.entity.PersonalInfo;
import kz.syllabus.entity.user.Instructor;
import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.repository.user.InstructorRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final PersonalInfoService  personalInfoService;

    public Instructor getById(Long id) throws NotFoundException {
        return instructorRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found"));
    }

    public List<Instructor> getBySyllabusId(Long id) throws NotFoundException {
        return instructorRepository
                .findBySyllabusId(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found"));
    }

    public void deleteAllBySyllabusId(Long id) throws NotFoundException {
        instructorRepository.deleteAllBySyllabusId(id);
    }

    public boolean existsByUserIdAndSyllabusId(Long userId, Long syllabusId) throws NotFoundException {
        return instructorRepository.existsByUserIdAndSyllabusId(userId, syllabusId);
    }

    @SneakyThrows
    public Instructor getInstructorByUserIdAndSyllabusId(Long userId, Long id) {
        return instructorRepository.findInstructorByUserIdAndSyllabusId(userId, id)
                .orElseThrow(() -> new NotFoundException("Instructor not found"));

    }

    public boolean existsBySyllabusId(Long id) {
        return instructorRepository.existsBySyllabusId(id);
    }

    public List<Instructor> getByUserId(Long userId) {
        return instructorRepository.findByUserId(userId);
    }

    public PersonalInfo getPersonalInfo(Instructor instructor) {
        return personalInfoService.getByUserId(instructor.getUserId());
    }

}
