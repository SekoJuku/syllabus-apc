package kz.syllabus.common.dto.response;


import lombok.Data;

@Data
public class ProgramDetailDtoResponse {
    private Long id;
    private Long programInfoId;
    private String lectureFof;
    private String practiceFof;
    private String iswFof;
    private String lectureLiterature;
    private String practiceLiterature;
    private String iswLiterature;
    private String lectureAssessment;
    private String practiceAssessment;
    private String iswAssessment;
}
