package kz.syllabus.persistence.model.syllabus;

import jakarta.persistence.*;
import kz.syllabus.dto.request.syllabus.SyllabusProgramDtoRequest;
import kz.syllabus.dto.response.syllabus.SyllabusProgramDtoResponse;
import kz.syllabus.persistence.model.Base;
import kz.syllabus.persistence.model.ProgramDetail;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "syllabus_program")
public class SyllabusProgram extends Base {

    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Long week;

    @ManyToOne
    @JoinColumn(name = "syllabus_id", referencedColumnName = "id")
    private Syllabus syllabus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "syllabus_program_id")
    private ProgramDetail programDetail;

    public static SyllabusProgram from(SyllabusProgramDtoRequest request) {
        return SyllabusProgram.builder()
                .lectureTheme(request.getLectureTheme())
                .practiceTheme(request.getPracticeTheme())
                .iswTheme(request.getIswTheme())
                .week(request.getWeek())
                .build();
    }

    public SyllabusProgramDtoResponse toDto() {
        SyllabusProgramDtoResponse response = new SyllabusProgramDtoResponse();
        response.setId(this.id);
        response.setSyllabusId(this.syllabus.getId());
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
        return Objects.equals(syllabus.getId(), that.syllabus.getId())
                && Objects.equals(lectureTheme, that.lectureTheme)
                && Objects.equals(practiceTheme, that.practiceTheme)
                && Objects.equals(iswTheme, that.iswTheme)
                && Objects.equals(week, that.week)
                && Objects.equals(programDetail, that.programDetail);
    }
}
