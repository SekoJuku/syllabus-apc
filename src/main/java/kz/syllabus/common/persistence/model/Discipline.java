package kz.syllabus.common.persistence.model;

import jakarta.persistence.*;
import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disciplines")
public class Discipline extends AbstractModel {

    private String name;
    private String language;
    private Long credits;
    private Long lectureHoursPerWeek;
    private Long practiceHoursPerWeek;
    private Long iswHoursPerWeek;
    private Long coordinatorId;

    @OneToMany(targetEntity = Syllabus.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private List<Syllabus> syllabuses;

}
