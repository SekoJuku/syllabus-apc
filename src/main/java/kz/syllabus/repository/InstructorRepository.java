package kz.syllabus.repository;

import kz.syllabus.entity.Instructor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    boolean existsByUserIdAndSyllabusId(Integer userId, Integer syllabusId);
    List<Instructor> getByUserId(Integer userId);
    List<Instructor> getBySyllabusId(Integer syllabusId);
    boolean existsBySyllabusId(Integer id);
    Instructor getInstructorByUserIdAndSyllabusId(Integer userId,Integer syllabusId);
    void deleteAllBySyllabusId(Integer syllabusId);
//    boolean existsByUserIdAndSyllabusId(Integer userId, Integer syllabusId);
}
