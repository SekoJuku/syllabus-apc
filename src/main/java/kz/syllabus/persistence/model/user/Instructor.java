package kz.syllabus.persistence.model.user;

import jakarta.persistence.*;
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

    @OneToOne
    private User user;

    @ManyToOne(targetEntity = Syllabus.class)
    @JoinColumn(name = "syllabus_id", referencedColumnName = "id")
    private Syllabus syllabus;
}
