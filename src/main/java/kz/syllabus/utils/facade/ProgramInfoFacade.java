package kz.syllabus.utils.facade;

import kz.syllabus.dto.requestDto.ProgramDetailDtoRequest;
import kz.syllabus.dto.requestDto.ProgramInfoDtoRequest;
import kz.syllabus.dto.responseDto.ProgramInfoDtoResponse;
import kz.syllabus.entity.ProgramInfo;

public class ProgramInfoFacade {
    public static ProgramInfoDtoResponse objectToDto(ProgramInfo programInfo) {
        ProgramInfoDtoResponse programInfoDtoResponse = new ProgramInfoDtoResponse();
        programInfoDtoResponse.setLectureTheme(programInfo.getLectureTheme());
        programInfoDtoResponse.setPracticeTheme(programInfo.getPracticeTheme());
        programInfoDtoResponse.setIswTheme(programInfo.getIswTheme());
        programInfoDtoResponse.setWeek(programInfo.getWeek());
        return programInfoDtoResponse;
    }
}
