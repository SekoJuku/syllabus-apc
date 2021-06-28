package kz.syllabus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_users")
public class TestUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String iin;
    private String name;
    private String sname;
    private String mname;
    private String academicDegree;
    private String academicRank;
    private String email;
    private String phone;
    private String description;
    private Integer coordinatorId;
}
