package kz.syllabus.common.persistence.model.user;

import jakarta.persistence.*;
import kz.syllabus.common.persistence.model.AbstractModel;
import kz.syllabus.common.persistence.model.syllabus.Syllabus;
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
public class Instructor extends AbstractModel {

    @OneToOne
    private User user;

    @ManyToOne(targetEntity = Syllabus.class)
    @JoinColumn(name = "syllabus_id", referencedColumnName = "id")
    private Syllabus syllabus;
}
