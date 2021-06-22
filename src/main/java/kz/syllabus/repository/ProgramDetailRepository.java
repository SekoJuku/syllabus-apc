package kz.syllabus.repository;

import kz.syllabus.entity.ProgramDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramDetailRepository extends JpaRepository<ProgramDetail, Integer> {
    ProgramDetail getBySyllabusProgramId(Integer syllabusProgramId);
    ProgramDetail getProgramDetailBySyllabusProgramId(Integer id);

    @Query(value = "select * from program_details where syllabus_program_id = ?;", nativeQuery = true)
    ProgramDetail getProgramDetailById(Integer syllabusProgramId);
}
