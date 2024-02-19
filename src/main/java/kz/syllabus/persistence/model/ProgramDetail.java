package kz.syllabus.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "program_details")
public class ProgramDetail extends Base {

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

}
