package kz.syllabus.repository;

import kz.syllabus.entity.user.Instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    boolean existsByUserIdAndSyllabusId(Long userId, Long syllabusId);
    List<Instructor> findByUserId(Long userId);
    Optional<List<Instructor>> findBySyllabusId(Long syllabusId);
    boolean existsBySyllabusId(Long id);
    Optional<Instructor> findInstructorByUserIdAndSyllabusId(Long userId,Long syllabusId);
    void deleteAllBySyllabusId(Long syllabusId);
}
