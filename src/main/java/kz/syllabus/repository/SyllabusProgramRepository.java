package kz.syllabus.repository;



import kz.syllabus.entity.SyllabusProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusProgramRepository extends JpaRepository<SyllabusProgram, Integer> {
    SyllabusProgram getBySyllabusId(Integer disciplineInfoId);
    List<SyllabusProgram> getAllBySyllabusId(Integer syllabusId);

    @Query(value = "select * from syllabus_program where syllabus_id = ?;", nativeQuery = true)
    SyllabusProgram getSyllabusProgramById(Integer syllabusId);
}
