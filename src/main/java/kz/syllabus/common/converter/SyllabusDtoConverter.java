package kz.syllabus.common.converter;

import kz.syllabus.common.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.common.dto.response.syllabus.SyllabusDtoResponse;
import kz.syllabus.common.dto.response.user.InstructorDtoResponse;
import kz.syllabus.common.mapper.ProgramDetailMapper;
import kz.syllabus.common.mapper.SyllabusProgramMapper;
import kz.syllabus.common.persistence.model.PersonalInfo;
import kz.syllabus.common.persistence.model.Postrequisite;
import kz.syllabus.common.persistence.model.Prerequisite;
import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.common.persistence.model.syllabus.SyllabusProgram;
import kz.syllabus.common.service.syllabus.PostrequisiteService;
import kz.syllabus.common.service.syllabus.PrerequisiteService;
import kz.syllabus.common.service.syllabus.ProgramDetailService;
import kz.syllabus.common.service.syllabus.SyllabusProgramService;
import kz.syllabus.common.service.user.PersonalInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SyllabusDtoConverter { // todo: remove services

    private final PersonalInfoService personalInfoService;
    private final SyllabusProgramMapper syllabusProgramMapper;
    private final ProgramDetailMapper programDetailMapper;
    private final PostrequisiteService postrequisiteService;
    private final PrerequisiteService prerequisiteService;
    private final SyllabusProgramService syllabusProgramService;
    private final ProgramDetailService programDetailService;

    public MainPageDtoResponse convertToMainPage(Syllabus item) {
        return MainPageDtoResponse.builder()
                                  .id(item.getId())
                                  .name(item.getName())
                                  .year(item.getYear())
                                  .disciplineName(item.getDiscipline().getName())
                                  .instructors(
                                          item.getInstructors().stream()
                                              .map(personalInfoService::get)
                                              .map(this::toMainPageDtoComponent)
                                              .toList()
                                  )
                                  .build();
    }

    public SyllabusDtoResponse convertToSyllabus(Syllabus source) {
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

    private InstructorDtoResponse toMainPageDtoComponent(PersonalInfo info) {
        return InstructorDtoResponse.builder()
                                    .id(info.getUser().getId())
                                    .name(info.getName())
                                    .lastname(info.getSname())
                                    .build();
    }

}
