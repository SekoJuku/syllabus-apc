package kz.syllabus.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "syllabus_program")
public class SyllabusProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer syllabusId;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Integer week;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "syllabusProgramId")
    private ProgramDetail programDetail;
}
