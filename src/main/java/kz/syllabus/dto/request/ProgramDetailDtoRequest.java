package kz.syllabus.dto.request;


import lombok.Data;

@Data
public class ProgramDetailDtoRequest {
    private Long id;
    private Long programInfoId;
    private String lectureFof;
    private String practiceFof;
    private String iswFof;
    private String lectureLiterature;
    private String practiceLiterature;
    private String iswLiterature;
    private String lectureAssessment;
    private String PracticeAssessment;
    private String IswAssessment;
    private Long week;
}
