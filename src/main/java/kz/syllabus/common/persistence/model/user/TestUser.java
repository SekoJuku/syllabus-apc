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
@Table(name = "test_users")
public class TestUser extends Base {

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
