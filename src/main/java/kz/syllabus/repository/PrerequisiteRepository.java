package kz.syllabus.repository;

import kz.syllabus.entity.Postrequisite;
import kz.syllabus.entity.Prerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Integer> {
    List<Prerequisite> findAllByDisciplineId(Integer disciplineId);
    List<Prerequisite> getAllBySyllabusId(Integer id);
    boolean existsByDisciplineIdAndSyllabusId(Integer disciplineId, Integer syllabusId);

    @Query(value = "select * from prerequisites where syllabus_id = ?;", nativeQuery = true)
    Prerequisite findPrerequisiteById(Integer syllabusId);
}
