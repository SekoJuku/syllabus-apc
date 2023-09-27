package kz.syllabus.persistence.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kz.syllabus.persistence.model.Base;
import kz.syllabus.persistence.model.syllabus.Syllabus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instructors")
public class Instructor extends Base {

    private Long userId;
    @ManyToOne(targetEntity = Syllabus.class)
    @JoinColumn(name = "syllabus_id", referencedColumnName = "id")
    private Syllabus syllabus;
}
