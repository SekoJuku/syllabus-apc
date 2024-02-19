package kz.syllabus.service.syllabus;

import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.persistence.model.syllabus.Syllabus;
import kz.syllabus.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.persistence.repository.SyllabusParamRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SyllabusParamService {
    private SyllabusParamRepository repository;

    @SneakyThrows
    public SyllabusParam getOrCreate(Syllabus syllabus) {

        return repository.findBySyllabusId(syllabus.getId())
                         .orElseGet(() -> repository.save(SyllabusParam.newEmptyParam(syllabus)));
    }

    public List<SyllabusParam> getAllSentToDean() {
        return repository.findAllByIsSentToDean(true);
    }

    @SneakyThrows
    public SyllabusParam sendToCoordinator(Long syllabusId) {
        return repository.findBySyllabusId(syllabusId)
                         .map(o -> {
                             o.setIsSentToCoordinator(true);
                             return repository.save(o);
                         })
                         .orElseThrow(() -> new NotFoundException("Syllabus not found!"));
    }

}
