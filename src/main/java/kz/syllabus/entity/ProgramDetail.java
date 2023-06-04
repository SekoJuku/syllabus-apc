package kz.syllabus.entity;

import jakarta.persistence.*;

import kz.syllabus.dto.request.ProgramDetailDtoRequest;
import kz.syllabus.dto.response.ProgramDetailDtoResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "program_details")
public class ProgramDetail extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long syllabusProgramId;
    private String lectureFof;
    private String practiceFof;
    private String iswFof;
    private String lectureLiterature;
    private String practiceLiterature;
    private String iswLiterature;
    private String lectureAssessment;
    private String practiceAssessment;
    private String iswAssessment;

    public static ProgramDetail fromRequest(ProgramDetailDtoRequest request) {
        return ProgramDetail.builder()
                .lectureFof(request.getLectureFof())
                .practiceFof(request.getPracticeFof())
                .iswFof(request.getIswFof())
                .lectureLiterature(request.getLectureLiterature())
                .practiceLiterature(request.getPracticeLiterature())
                .iswLiterature(request.getIswLiterature())
                .lectureAssessment(request.getLectureAssessment())
                .practiceAssessment(request.getPracticeAssessment())
                .iswAssessment(request.getIswAssessment())
                .build();
    }

    public ProgramDetailDtoResponse toDto() {
        ProgramDetailDtoResponse response = new ProgramDetailDtoResponse();
        response.setId(this.id);
        response.setProgramInfoId(this.syllabusProgramId);
        response.setLectureFof(this.lectureFof);
        response.setPracticeFof(this.practiceFof);
        response.setIswFof(this.iswFof);
        response.setLectureLiterature(this.lectureLiterature);
        response.setPracticeLiterature(this.practiceLiterature);
        response.setIswLiterature(this.iswLiterature);
        response.setLectureAssessment(this.lectureAssessment);
        response.setPracticeAssessment(this.practiceAssessment);
        response.setIswAssessment(this.iswAssessment);
        return response;
    }
}
