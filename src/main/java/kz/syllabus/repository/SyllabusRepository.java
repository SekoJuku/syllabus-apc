package kz.syllabus.repository;

import kz.syllabus.entity.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Integer> {
    boolean existsByDisciplineId(Integer disciplineId);
//    boolean existsByDisciplineId(Integer disciplineId);
}
