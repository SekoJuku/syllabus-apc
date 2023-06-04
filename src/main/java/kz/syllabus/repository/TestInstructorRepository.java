package kz.syllabus.repository;

import kz.syllabus.entity.user.TestInstructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestInstructorRepository extends JpaRepository<TestInstructor, Long> {
    TestInstructor getBySyllabusIdAndUserId(Long syllabusId, Long userId);

    boolean existsBySyllabusIdAndUserId(Long syllabusId, Long userId);

    boolean existsBySyllabusId(Long id);

    TestInstructor getBySyllabusId(Long id);
}
