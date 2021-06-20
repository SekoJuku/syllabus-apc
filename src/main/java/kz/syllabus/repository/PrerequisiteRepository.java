package kz.syllabus.repository;

import kz.syllabus.entity.Postrequisite;
import kz.syllabus.entity.Prerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Integer> {
//    List<Prerequisite> findAllByDisciplineId(Integer disciplineId);
}
