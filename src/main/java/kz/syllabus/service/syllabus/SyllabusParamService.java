package kz.syllabus.service.syllabus;

import kz.syllabus.entity.syllabus.Syllabus;
import kz.syllabus.entity.syllabus.SyllabusParam;
import kz.syllabus.entity.user.Instructor;
import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.repository.syllabus.SyllabusParamRepository;
import kz.syllabus.service.user.InstructorService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SyllabusParamService {
    private SyllabusParamRepository repository;
    private InstructorService       instructorService;

    @SneakyThrows
    public SyllabusParam getBySyllabusId(Long syllabusId) {
        return repository.findBySyllabusId(syllabusId).orElseThrow(
                () -> new NotFoundException("Syllabus param not found")
        );
    }

    @SneakyThrows
    public SyllabusParam getOrCreateSyllabusParam(Syllabus syllabus) {
        if (!instructorService.existsBySyllabusId(syllabus.getId()))
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
        List<Instructor> instructors = instructorService.getBySyllabusId(syllabus.getId());
        Instructor instructor = instructors.stream().findFirst()
                .orElseThrow(() -> new Exception("Instructor not found"));
        return this.getBySyllabusId(instructor.getSyllabus().getId());
    }

    public SyllabusParam save(SyllabusParam syllabusParam) {
        return repository.save(syllabusParam);
    }

    public boolean existsBySyllabusIdAndIsActive(Long id, boolean b) {
        return repository.existsBySyllabusIdAndIsActive(id, b);
    }

    public List<SyllabusParam> getAllSentToDean() {
        return repository.findAllByIsSentToDean(true);
    }

    public SyllabusParam setSentToCoordinator(Long syllabusId) {
        SyllabusParam param = this.getBySyllabusId(syllabusId);
        param.setIsSentToCoordinator(true);
        return this.save(param);
    }
}
