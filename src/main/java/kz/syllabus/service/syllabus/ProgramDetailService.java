package kz.syllabus.service.syllabus;

import kz.syllabus.entity.ProgramDetail;
import kz.syllabus.repository.syllabus.ProgramDetailRepository;
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
