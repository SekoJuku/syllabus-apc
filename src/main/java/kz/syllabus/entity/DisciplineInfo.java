package kz.syllabus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discipline_info")
public class DisciplineInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    private String name;
    private Integer disciplineId;
    private Integer credits;
    private String aim;
    private String tasks;
    private String results;
    private String methodology;

}
