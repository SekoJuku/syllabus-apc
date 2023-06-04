package kz.syllabus.repository;

import kz.syllabus.entity.user.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //    Optional<Role> findByName(String name);
}
