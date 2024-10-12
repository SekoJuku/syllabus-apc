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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends Base {

    private String name;
}
