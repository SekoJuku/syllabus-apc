package kz.syllabus.repository;

import kz.syllabus.entity.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestUserRepository extends JpaRepository<TestUser, Integer> {
    TestUser getByIin(String iin);
}
