package kz.syllabus.common.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import kz.syllabus.common.persistence.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "personal_info")
public class PersonalInfo extends AbstractModel {

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
