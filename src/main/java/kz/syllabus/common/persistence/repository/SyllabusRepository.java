package kz.syllabus.common.persistence.repository;

import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {
    List<Syllabus> getAllByDisciplineIdAndYear(Long disciplineId, String year);
}
