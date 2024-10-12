package kz.syllabus.common.persistence.repository;

import kz.syllabus.common.persistence.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //    Optional<Role> findByName(String name);
}
