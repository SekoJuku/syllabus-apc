package kz.syllabus.service.syllabus;


import kz.syllabus.dto.request.ProgramDetailDtoRequest;
import kz.syllabus.dto.request.syllabus.SyllabusProgramDtoRequest;
import kz.syllabus.entity.ProgramDetail;
import kz.syllabus.entity.syllabus.Syllabus;
import kz.syllabus.entity.syllabus.SyllabusProgram;
import kz.syllabus.repository.syllabus.SyllabusProgramRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@AllArgsConstructor
public class SyllabusProgramService {
    private final SyllabusProgramRepository repository;
    private final ProgramDetailService      programDetailService;

    public List<SyllabusProgram> getAllBySyllabusId(Long syllabusId) {
        return repository.getAllBySyllabusId(syllabusId);
    }

    public SyllabusProgram save(SyllabusProgram syllabusProgram) {
        return repository.save(syllabusProgram);
    }

    public void createAll(List<SyllabusProgramDtoRequest> requests,
                          List<ProgramDetailDtoRequest> programDetailDtoRequests, Syllabus syllabus) {
        requests.forEach(
                request -> {
                    SyllabusProgram syllabusProgram =
                            SyllabusProgram.builder()
                                    .syllabusId(syllabus.getId())
                                    .lectureTheme(request.getLectureTheme())
                                    .practiceTheme(request.getPracticeTheme())
                                    .iswTheme(request.getIswTheme())
                                    .week(request.getWeek())
                                    .build();
                    SyllabusProgram SyllabusProgram =
                            this.save(syllabusProgram);

                    programDetailDtoRequests.stream().filter(i -> i.getWeek().equals(syllabusProgram.getWeek()))
                            .findFirst().ifPresent(item -> {
                                ProgramDetail programDetail = ProgramDetail.builder()
                                        .syllabusProgramId(SyllabusProgram.getId())
                                        .lectureFof(item.getLectureFof())
                                        .practiceFof(item.getPracticeFof())
                                        .iswFof(item.getIswFof())
                                        .lectureLiterature(item.getLectureLiterature())
                                        .practiceLiterature(item.getPracticeLiterature())
                                        .iswLiterature(item.getIswLiterature())
                                        .lectureAssessment(item.getLectureAssessment())
                                        .practiceAssessment(item.getPracticeAssessment())
                                        .iswAssessment(item.getIswAssessment())
                                        .build();
                                programDetailService.save(programDetail);
                            });
                });
    }
}
