package kz.syllabus.service.syllabus;


import kz.syllabus.dto.request.ProgramDetailDtoRequest;
import kz.syllabus.dto.request.syllabus.SyllabusProgramDtoRequest;
import kz.syllabus.persistence.model.ProgramDetail;
import kz.syllabus.persistence.model.syllabus.Syllabus;
import kz.syllabus.persistence.model.syllabus.SyllabusProgram;
import kz.syllabus.persistence.repository.SyllabusProgramRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@AllArgsConstructor
public class SyllabusProgramService {

    private final SyllabusProgramRepository repository;
    private final ProgramDetailService programDetailService;

    public List<SyllabusProgram> getAllBySyllabusId(Long syllabusId) {
        return repository.getAllBySyllabusId(syllabusId);
    }

    public void createAll(List<SyllabusProgramDtoRequest> requests,
                          List<ProgramDetailDtoRequest> programDetailDtoRequests, Syllabus syllabus) {
        requests.forEach(request -> {
            var syllabusProgram = SyllabusProgram.from(request);
            syllabusProgram.setSyllabus(syllabus);
            repository.save(syllabusProgram);

            programDetailDtoRequests.stream()
                                    .filter(i -> i.getWeek().equals(syllabusProgram.getWeek()))
                                    .findFirst().ifPresent(item -> {
                                        var programDetail = ProgramDetail.fromRequest(item);
                                        programDetail.setSyllabusProgramId(syllabusProgram.getId());
                                        programDetailService.save(programDetail);
                                    });
        });
    }
}
