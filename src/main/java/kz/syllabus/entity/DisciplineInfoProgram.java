package kz.syllabus.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discipline_info_program")
public class DisciplineInfoProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer instructorId;
    private Integer disciplineInfoId;
    private Integer evaluationId;
}
