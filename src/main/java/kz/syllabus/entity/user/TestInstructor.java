package kz.syllabus.entity.user;

import jakarta.persistence.*;

import kz.syllabus.entity.Base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_instructors")
public class TestInstructor extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long syllabusId;
}
