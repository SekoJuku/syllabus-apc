package kz.syllabus.common.persistence.repository;

import kz.syllabus.common.persistence.model.user.TestInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestInstructorRepository extends JpaRepository<TestInstructor, Long> {

    boolean existsBySyllabusIdAndUserId(Long syllabusId, Long userId);

    boolean existsBySyllabusId(Long id);

}
