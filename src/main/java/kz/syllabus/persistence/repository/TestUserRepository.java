package kz.syllabus.persistence.repository;

import kz.syllabus.persistence.model.user.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestUserRepository extends JpaRepository<TestUser, Long> {
    Optional<TestUser> findByIin(String iin);
}
