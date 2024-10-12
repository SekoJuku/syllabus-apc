package kz.syllabus.common.persistence.model.syllabus;

import jakarta.persistence.*;
import kz.syllabus.common.persistence.model.AbstractModel;
import kz.syllabus.common.persistence.model.Discipline;
import kz.syllabus.common.persistence.model.Rubric;
import kz.syllabus.common.persistence.model.user.Instructor;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "syllabus")
public class Syllabus extends AbstractModel {

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
