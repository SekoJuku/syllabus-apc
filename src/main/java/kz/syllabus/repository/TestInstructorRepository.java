package kz.syllabus.repository;

import kz.syllabus.entity.TestInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestInstructorRepository extends JpaRepository<TestInstructor, Integer> {
    TestInstructor getBySyllabusIdAndUserId(Integer syllabusId, Integer userId);
    boolean existsBySyllabusIdAndUserId(Integer syllabusId, Integer userId);
    boolean existsBySyllabusId(Integer id);
}
