package kz.syllabus.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disciplines")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String language;
    private Integer credits;
    private Integer lectureHoursPerWeek;
    private Integer practiceHoursPerWeek;
    private Integer iswHoursPerWeek;
    private Integer coordinatorId;
    @OneToMany(targetEntity = Syllabus.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "disciplineId", referencedColumnName = "id")
    private List<Syllabus> syllabuses;
    @OneToMany(targetEntity = Prerequisite.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "disciplineId", referencedColumnName = "id")
    private List<Prerequisite> prerequisites;
    @OneToMany(targetEntity = Postrequisite.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "disciplineId", referencedColumnName = "id")
    private List<Postrequisite> postrequisites;
}
