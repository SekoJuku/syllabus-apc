package kz.syllabus.utils.facade;

import kz.syllabus.dto.responseDto.ProgramDetailDtoResponse;
import kz.syllabus.entity.ProgramDetail;

public class ProgramDetailFacade {
    public static ProgramDetailDtoResponse objectToDto(ProgramDetail programDetail) {
        ProgramDetailDtoResponse response = new ProgramDetailDtoResponse();
        response.setProgramInfoId(programDetail.getProgramInfoId());
        response.setLectureFof(programDetail.getLectureFof());
        response.setPracticeFof(programDetail.getPracticeFof());
        response.setIswFof(programDetail.getIswFof());
        response.setLectureLiterature(programDetail.getLectureLiterature());
        response.setPracticeLiterature(programDetail.getPracticeLiterature());
        response.setIswLiterature(programDetail.getIswLiterature());
        response.setLectureAssessment(programDetail.getLectureAssessment());
        response.setPracticeAssessment(programDetail.getPracticeAssessment());
        response.setIswAssessment(programDetail.getIswAssessment());
        return response;
    }
}
