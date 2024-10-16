package kz.syllabus.common.persistence.repository;

import kz.syllabus.common.persistence.model.Prerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Long> {

    List<Prerequisite> getAllBySyllabusId(Long id);

    boolean existsByDisciplineIdAndSyllabusId(Long disciplineId, Long syllabusId);
}
