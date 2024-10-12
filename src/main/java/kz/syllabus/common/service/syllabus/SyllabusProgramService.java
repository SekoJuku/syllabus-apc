package kz.syllabus.common.service.syllabus;


import kz.syllabus.common.dto.request.ProgramDetailDtoRequest;
import kz.syllabus.common.dto.request.syllabus.SyllabusProgramDtoRequest;
import kz.syllabus.common.mapper.ProgramDetailMapper;
import kz.syllabus.common.mapper.SyllabusProgramMapper;
import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.common.persistence.model.syllabus.SyllabusProgram;
import kz.syllabus.common.persistence.repository.SyllabusProgramRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@AllArgsConstructor
public class SyllabusProgramService {

    private final SyllabusProgramMapper syllabusProgramMapper;
    private final ProgramDetailMapper programDetailMapper;
    private final SyllabusProgramRepository repository;
    private final ProgramDetailService programDetailService;

    public List<SyllabusProgram> getAllBySyllabusId(Long syllabusId) {
        return repository.getAllBySyllabusId(syllabusId);
    }

    public void createAll(
            List<SyllabusProgramDtoRequest> requests,
            List<ProgramDetailDtoRequest> programDetailDtoRequests,
            Syllabus syllabus
    ) {
        requests.forEach(request -> {
            var syllabusProgram = syllabusProgramMapper.map(request);
            syllabusProgram.setSyllabus(syllabus);
            repository.save(syllabusProgram);

            programDetailDtoRequests.stream()
                                    .filter(i -> i.getWeek().equals(syllabusProgram.getWeek()))
                                    .findFirst()
                                    .map(programDetailMapper::map)
                                    .ifPresent(item -> {
                                        item.setSyllabusProgramId(syllabusProgram.getId());
                                        programDetailService.save(item);
                                    });
        });
    }
}
