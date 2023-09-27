package kz.syllabus.persistence;

import kz.syllabus.persistence.model.user.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Optional<List<Instructor>> findBySyllabusId(Long syllabusId);

    boolean existsBySyllabusId(Long id);

    void deleteAllBySyllabusId(Long syllabusId);
}
