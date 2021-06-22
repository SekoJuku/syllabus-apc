package kz.syllabus.repository;

import kz.syllabus.entity.SyllabusProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusProgramRepository extends JpaRepository<SyllabusProgram, Integer> {
    SyllabusProgram getBySyllabusId(Integer disciplineInfoId);
    List<SyllabusProgram> getAllBySyllabusId(Integer syllabusId);
}
