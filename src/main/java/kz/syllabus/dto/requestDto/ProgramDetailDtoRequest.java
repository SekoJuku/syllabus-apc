package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class ProgramDetailDtoRequest {
    private Integer id;
    private Integer programInfoId;
    private String lectureFof;
    private String practiceFof;
    private String iswFof;
    private String lectureLiterature;
    private String practiceLiterature;
    private String iswLiterature;
    private String lectureAssessment;
    private String PracticeAssessment;
    private String IswAssessment;
}
