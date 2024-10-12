package kz.syllabus.common.persistence.repository;

import kz.syllabus.common.persistence.model.Postrequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostrequisiteRepository extends JpaRepository<Postrequisite, Long> {

    List<Postrequisite> getAllBySyllabusId(Long syllabusId);

    boolean existsByDisciplineIdAndSyllabusId(Long disciplineId, Long syllabusId);
}
