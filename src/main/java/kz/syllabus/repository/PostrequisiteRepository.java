package kz.syllabus.repository;

import kz.syllabus.entity.Postrequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostrequisiteRepository extends JpaRepository<Postrequisite, Integer> {
    List<Postrequisite> findAllByDisciplineId(Integer disciplineId);
    List<Postrequisite> getAllBySyllabusId(Integer syllabusId);
    boolean existsByDisciplineIdAndSyllabusId(Integer disciplineId, Integer syllabusId);

    @Query(value = "select * from postrequisites where syllabus_id = ?;", nativeQuery = true)
    Postrequisite findPostrequisiteById(Integer syllabusId);
}
