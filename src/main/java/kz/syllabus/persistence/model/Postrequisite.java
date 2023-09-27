package kz.syllabus.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "postrequisites")
public class Postrequisite extends Base {

    private Long disciplineId;
    private Long syllabusId;
}
