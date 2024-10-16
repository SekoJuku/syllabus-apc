package kz.syllabus.common.persistence.repository;

import kz.syllabus.common.persistence.model.user.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    boolean existsBySyllabusId(Long id);

    void deleteAllBySyllabusId(Long syllabusId);
}
