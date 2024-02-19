package kz.syllabus.converter;

import kz.syllabus.dto.response.syllabus.SyllabusDtoResponse;
import kz.syllabus.mapper.ProgramDetailMapper;
import kz.syllabus.mapper.SyllabusProgramMapper;
import kz.syllabus.persistence.model.Postrequisite;
import kz.syllabus.persistence.model.Prerequisite;
import kz.syllabus.persistence.model.syllabus.Syllabus;
import kz.syllabus.persistence.model.syllabus.SyllabusProgram;
import kz.syllabus.service.syllabus.PostrequisiteService;
import kz.syllabus.service.syllabus.PrerequisiteService;
import kz.syllabus.service.syllabus.ProgramDetailService;
import kz.syllabus.service.syllabus.SyllabusProgramService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SyllabusSyllabusDtoResponseConverter implements Converter<Syllabus, SyllabusDtoResponse> {

    private final SyllabusProgramMapper syllabusProgramMapper;
    private final ProgramDetailMapper programDetailMapper;
    private final PostrequisiteService postrequisiteService;
    private final PrerequisiteService prerequisiteService;
    private final SyllabusProgramService syllabusProgramService;
    private final ProgramDetailService programDetailService;

    @Override
    @SneakyThrows
    public SyllabusDtoResponse convert(Syllabus source) {
        final var response = new SyllabusDtoResponse();
        response.setId(source.getId());
        response.setDisciplineId(source.getDiscipline().getId());
        response.setCredits(source.getCredits());
        response.setAim(source.getAim());
        response.setTasks(source.getTasks());
        response.setResults(source.getResults());
        response.setMethodology(source.getMethodology());

        response.setLectureHoursPerWeek(source.getDiscipline().getLectureHoursPerWeek());
        response.setPracticeHoursPerWeek(source.getDiscipline().getPracticeHoursPerWeek());
        response.setIswHoursPerWeek(source.getDiscipline().getIswHoursPerWeek());

        final var postrequisiteList = postrequisiteService.getAllBySyllabusId(source.getId());
        final var prerequisiteList = prerequisiteService.getAllBySyllabusId(source.getId());

        response.setPrerequisites(prerequisiteList.stream().map(Prerequisite::getId).toList());
        response.setPostrequisites(postrequisiteList.stream().map(Postrequisite::getId).toList());

        final var syllabusProgramList = syllabusProgramService.getAllBySyllabusId(source.getId());

        response.setSyllabusProgram(syllabusProgramList.stream().map(syllabusProgramMapper::map).toList());

        response.setProgramDetails(
                syllabusProgramList.stream()
                                   .map(SyllabusProgram::getId)
                                   .map(programDetailService::getBySyllabusId)
                                   .map(programDetailMapper::map)
                                   .toList());

        return response;
    }
}
