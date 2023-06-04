package kz.syllabus.entity;

import jakarta.persistence.*;

import kz.syllabus.entity.syllabus.Syllabus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disciplines")
public class Discipline extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
