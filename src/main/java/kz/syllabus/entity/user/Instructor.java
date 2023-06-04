package kz.syllabus.entity.user;

import jakarta.persistence.*;

import kz.syllabus.entity.Base;
import kz.syllabus.entity.syllabus.Syllabus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instructors")
public class Instructor extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    @ManyToOne(targetEntity = Syllabus.class)
    @JoinColumn(name = "syllabus_id", referencedColumnName = "id")
    private Syllabus syllabus;
}
