package kz.syllabus.common.persistence.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kz.syllabus.common.persistence.model.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_instructors")
public class TestInstructor extends Base {

    private Long userId;
    private Long syllabusId;
}
