package kz.syllabus.repository;

import kz.syllabus.entity.Postrequisite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostrequisiteRepository extends JpaRepository<Postrequisite, Long> {
    List<Postrequisite> findAllByDisciplineId(Long disciplineId);

    List<Postrequisite> getAllBySyllabusId(Long syllabusId);

    boolean existsByDisciplineIdAndSyllabusId(Long disciplineId, Long syllabusId);
}
