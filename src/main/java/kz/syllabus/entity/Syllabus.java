package kz.syllabus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "syllabus")
public class Syllabus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer disciplineId;
    private String name;
    private Integer credits;
    private String aim;
    private String tasks;
    private String results;
    private String methodology;
    private String year;
    private Integer evaluationId;
    private String competences;
    private Integer rubricId;
    @OneToMany(fetch = FetchType.EAGER,targetEntity = SyllabusProgram.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "syllabusId", referencedColumnName = "id")
    private List<SyllabusProgram> syllabusProgram;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "syllabusId")
    private SyllabusParam syllabusParam;
    @OneToOne(fetch = FetchType.EAGER,targetEntity = Instructor.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "syllabusId")
    private Instructor instructor;
}
