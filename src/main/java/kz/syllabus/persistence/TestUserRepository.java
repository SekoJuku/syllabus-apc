package kz.syllabus.persistence;

import kz.syllabus.persistence.model.user.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestUserRepository extends JpaRepository<TestUser, Long> {
    TestUser getByIin(String iin);
}
