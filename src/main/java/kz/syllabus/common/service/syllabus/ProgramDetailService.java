package kz.syllabus.common.service.syllabus;

import kz.syllabus.common.persistence.model.ProgramDetail;
import kz.syllabus.common.persistence.repository.ProgramDetailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
@AllArgsConstructor
public class ProgramDetailService {
    private final ProgramDetailRepository repository;

    public ProgramDetail getBySyllabusId(Long syllabusId) {
        return repository.getBySyllabusProgramId(syllabusId);
    }

    public ProgramDetail save(ProgramDetail programDetail) {
        return repository.save(programDetail);
    }
}
