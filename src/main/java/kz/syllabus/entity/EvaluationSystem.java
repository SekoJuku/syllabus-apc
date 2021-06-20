package kz.syllabus.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluation_systems")
public class EvaluationSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @OneToOne(targetEntity = Syllabus.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "evaluationId")
    private Syllabus syllabus;
}
