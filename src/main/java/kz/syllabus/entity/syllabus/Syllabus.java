package kz.syllabus.entity.syllabus;

import jakarta.persistence.*;

import kz.syllabus.dto.response.syllabus.SyllabusDtoResponse;
import kz.syllabus.entity.Base;
import kz.syllabus.entity.Discipline;
import kz.syllabus.entity.Rubric;
import kz.syllabus.entity.user.Instructor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "syllabus")
public class Syllabus extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Discipline.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private Discipline discipline;
    private String name;
    private Long credits;
    private String aim;
    private String tasks;
    private String results;
    private String methodology;
    private String year;
    private String competences;
    @ManyToOne(targetEntity = Rubric.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "rubric_id", referencedColumnName = "id")
    private Rubric rubric;

    @OneToMany(targetEntity = SyllabusProgram.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "syllabus_id", referencedColumnName = "id")
    private List<SyllabusProgram> syllabusPrograms;

    @ManyToOne(cascade = CascadeType.ALL)
    private SyllabusParam syllabusParam;

    @OneToMany(targetEntity = Instructor.class, cascade = CascadeType.ALL)
    private List<Instructor> instructors;

    public SyllabusDtoResponse toDto() {
        SyllabusDtoResponse response = new SyllabusDtoResponse();
        response.setId(this.id);
        response.setDisciplineId(this.discipline.getId());
        response.setCredits(this.credits);
        response.setAim(this.aim);
        response.setTasks(this.tasks);
        response.setResults(this.results);
        response.setMethodology(this.methodology);
        return response;
    }

    @Override
    public boolean equals(Object o) { // TODO: refactor equals to proper way. Create custom comparator for Syllabus
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Syllabus syllabus = (Syllabus) o;
        return Objects.equals(discipline, syllabus.discipline)
                && Objects.equals(name, syllabus.name)
                && Objects.equals(credits, syllabus.credits)
                && Objects.equals(aim, syllabus.aim)
                && Objects.equals(tasks, syllabus.tasks)
                && Objects.equals(results, syllabus.results)
                && Objects.equals(methodology, syllabus.methodology)
                && Objects.equals(year, syllabus.year)
                && Objects.equals(rubric, syllabus.rubric)
                && Objects.equals(competences, syllabus.competences)
                && Objects.equals(syllabusPrograms, syllabus.syllabusPrograms)
                && Objects.equals(syllabusParam, syllabus.syllabusParam);
    }
}
