package kz.syllabus.repository;

import kz.syllabus.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
//    List<Instructor> getByUserId(Integer userId);
//    boolean existsByUserIdAndDisciplineInfoId(Integer userId, Integer disciplineId);
}
