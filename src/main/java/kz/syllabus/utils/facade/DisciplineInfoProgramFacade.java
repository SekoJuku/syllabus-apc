package kz.syllabus.utils.facade;

import kz.syllabus.dto.responseDto.SyllabusDtoResponse;
import kz.syllabus.entity.DisciplineInfoProgram;

public class DisciplineInfoProgramFacade {
    public static SyllabusDtoResponse objectToSyllabusDto(DisciplineInfoProgram disciplineInfoProgram, SyllabusDtoResponse response) {
        response.setEvaluationId(disciplineInfoProgram.getEvaluationId());
        response.setCompetencies(disciplineInfoProgram.getCompetencies());
        return response;
    }
}
