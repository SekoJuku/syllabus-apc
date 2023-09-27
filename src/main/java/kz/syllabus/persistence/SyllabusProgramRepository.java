package kz.syllabus.persistence;

import kz.syllabus.persistence.model.syllabus.SyllabusProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusProgramRepository extends JpaRepository<SyllabusProgram, Long> {

    List<SyllabusProgram> getAllBySyllabusId(Long syllabusId);
}
