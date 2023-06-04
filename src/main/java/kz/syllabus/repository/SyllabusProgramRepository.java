package kz.syllabus.repository;

import kz.syllabus.entity.syllabus.SyllabusProgram;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusProgramRepository extends JpaRepository<SyllabusProgram, Long> {
    SyllabusProgram getBySyllabusId(Long disciplineInfoId);

    List<SyllabusProgram> getAllBySyllabusId(Long syllabusId);
}
