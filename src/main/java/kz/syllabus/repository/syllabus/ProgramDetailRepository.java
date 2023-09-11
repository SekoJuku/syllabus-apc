package kz.syllabus.repository.syllabus;

import kz.syllabus.entity.ProgramDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramDetailRepository extends JpaRepository<ProgramDetail, Long> {
    ProgramDetail getBySyllabusProgramId(Long syllabusProgramId);

}