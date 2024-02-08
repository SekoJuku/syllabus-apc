package kz.syllabus.service.syllabus;

import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.persistence.model.syllabus.Syllabus;
import kz.syllabus.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.persistence.repository.SyllabusParamRepository;
import kz.syllabus.service.user.InstructorService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SyllabusParamService {
    private SyllabusParamRepository repository;
    private InstructorService instructorService;

    @SneakyThrows
    public SyllabusParam getBySyllabusId(Long syllabusId) {
        return repository.findBySyllabusId(syllabusId).orElseThrow(
                () -> new NotFoundException("Syllabus param not found")
        );
    }

    @SneakyThrows
    public SyllabusParam getOrCreate(Syllabus syllabus) {
        if (!instructorService.existsBySyllabusId(syllabus.getId())) {
            return this.create(syllabus);
        }
        final var instructors = instructorService.getBySyllabusId(syllabus.getId());
        final var instructor = instructors.stream().findFirst()
                                          .orElseThrow(() -> new NotFoundException("Instructor not found"));
        return this.getBySyllabusId(instructor.getSyllabus().getId());
    }

    public SyllabusParam save(SyllabusParam syllabusParam) {
        return repository.save(syllabusParam);
    }

    public boolean activeBySyllabusId(Long id) {
        return repository.existsBySyllabusIdAndIsActive(id, true);
    }

    public List<SyllabusParam> getAllSentToDean() {
        return repository.findAllByIsSentToDean(true);
    }

    public SyllabusParam sendToCoordinator(Long syllabusId) {
        SyllabusParam param = this.getBySyllabusId(syllabusId);
        param.setIsSentToCoordinator(true);
        return this.save(param);
    }

    private SyllabusParam create(Syllabus syllabus) {
        return SyllabusParam.builder()
                            .syllabus(syllabus)
                            .isFinal(false)
                            .isSendable(false)
                            .isApprovedByCoordinator(false)
                            .isSentToCoordinator(false)
                            .isApprovedByDean(false)
                            .isSentToDean(false)
                            .isSendable(false)
                            .isActive(false)
                            .build();
    }
}
