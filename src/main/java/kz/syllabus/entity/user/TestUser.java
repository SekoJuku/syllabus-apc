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
@Table(name = "test_users")
public class TestUser extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iin;
    private String name;
    private String sname;
    private String mname;
    private String academicDegree;
    private String academicRank;
    private String email;
    private String phone;
    private String description;
    private Long coordinatorId;
}
