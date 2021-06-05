package kz.syllabus.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Integer IswHoursPerWeek;
}
