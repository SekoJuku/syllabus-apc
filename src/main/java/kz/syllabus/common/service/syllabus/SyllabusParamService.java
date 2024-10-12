package kz.syllabus.common.service.syllabus;

import kz.syllabus.common.exception.domain.NotFoundException;
import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.common.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.common.persistence.repository.SyllabusParamRepository;
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
