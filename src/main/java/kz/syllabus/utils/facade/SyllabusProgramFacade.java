package kz.syllabus.utils.facade;

import kz.syllabus.dto.responseDto.FullSyllabusDtoResponse;
import kz.syllabus.dto.responseDto.SyllabusProgramDtoResponse;
import kz.syllabus.entity.SyllabusProgram;

public class SyllabusProgramFacade {
    public static SyllabusProgramDtoResponse objectToDto(SyllabusProgram syllabusProgram) {
        SyllabusProgramDtoResponse response = new SyllabusProgramDtoResponse();
        response.setId(syllabusProgram.getId());
        response.setSyllabusId(syllabusProgram.getSyllabusId());
        response.setLectureTheme(syllabusProgram.getLectureTheme());
        response.setPracticeTheme(syllabusProgram.getPracticeTheme());
        response.setIswTheme(syllabusProgram.getIswTheme());
        response.setWeek(syllabusProgram.getWeek());
        return response;
    }
}
