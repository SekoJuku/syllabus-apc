package kz.syllabus.common.persistence.model;

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
@Table(name = "prerequisites")
public class Prerequisite extends AbstractModel {

    private Long disciplineId;
    private Long syllabusId;
}
