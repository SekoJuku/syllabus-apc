package kz.syllabus.entity.syllabus;

import jakarta.persistence.*;

import kz.syllabus.dto.response.syllabus.SyllabusProgramDtoResponse;
import kz.syllabus.entity.Base;
import kz.syllabus.entity.ProgramDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "syllabus_program")
public class SyllabusProgram extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long syllabusId;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Long week;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "syllabusProgramId")
    private ProgramDetail programDetail;

    public SyllabusProgramDtoResponse toDto() {
        SyllabusProgramDtoResponse response = new SyllabusProgramDtoResponse();
        response.setId(this.id);
        response.setSyllabusId(this.syllabusId);
        response.setLectureTheme(this.lectureTheme);
        response.setPracticeTheme(this.practiceTheme);
        response.setIswTheme(this.iswTheme);
        response.setWeek(this.week);
        return response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SyllabusProgram that = (SyllabusProgram) o;
        return Objects.equals(syllabusId, that.syllabusId)
                && Objects.equals(lectureTheme, that.lectureTheme)
                && Objects.equals(practiceTheme, that.practiceTheme)
                && Objects.equals(iswTheme, that.iswTheme)
                && Objects.equals(week, that.week)
                && Objects.equals(programDetail, that.programDetail);
    }
}
