package kz.syllabus.persistence.repository;

import kz.syllabus.persistence.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //    Optional<Role> findByName(String name);
}
