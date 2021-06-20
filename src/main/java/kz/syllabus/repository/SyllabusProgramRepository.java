package kz.syllabus.repository;

import kz.syllabus.entity.SyllabusProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyllabusProgramRepository extends JpaRepository<SyllabusProgram, Integer> {
//    SyllabusProgram getByDisciplineInfoId(Integer disciplineInfoId);
}
