package kz.syllabus.utils.facade;

import kz.syllabus.dto.responseDto.DisciplineInfoDtoResponse;
import kz.syllabus.dto.responseDto.SyllabusDtoResponse;
import kz.syllabus.entity.DisciplineInfo;

public class DisciplineInfoFacade {
    public static DisciplineInfoDtoResponse objectToDto(DisciplineInfo disciplineInfo) {
        DisciplineInfoDtoResponse response = new DisciplineInfoDtoResponse();
        if(disciplineInfo.getId() != null) {
            response.setId(disciplineInfo.getId());
        }
        response.setDisciplineId(disciplineInfo.getDisciplineId());
        response.setCredits(disciplineInfo.getCredits());
        response.setAim(disciplineInfo.getAim());
        response.setTasks(disciplineInfo.getTasks());
        response.setResults(disciplineInfo.getResults());
        response.setMethodology(disciplineInfo.getMethodology());
        return response;
    }

    public static SyllabusDtoResponse objectToSyllabusDto(DisciplineInfo disciplineInfo, SyllabusDtoResponse response) {
        if(disciplineInfo.getId() != null) {
            response.setId(disciplineInfo.getId());
        }
        response.setDisciplineId(disciplineInfo.getDisciplineId());
        response.setCredits(disciplineInfo.getCredits());
        response.setAim(disciplineInfo.getAim());
        response.setTasks(disciplineInfo.getTasks());
        response.setResults(disciplineInfo.getResults());
        response.setMethodology(disciplineInfo.getMethodology());
        return response;
    }
}
