package kz.syllabus.entity;

import jakarta.persistence.*;

import kz.syllabus.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "personal_info")
public class PersonalInfo extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private String sname;
    private String name;
    private String mname;
    private String academicDegree;
    private String academicRank;
    private Long positionId;
    private String email;
    private String phone;
    private String description;
}
