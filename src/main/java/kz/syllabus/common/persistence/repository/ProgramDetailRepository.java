package kz.syllabus.common.persistence.repository;

import kz.syllabus.common.persistence.model.ProgramDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramDetailRepository extends JpaRepository<ProgramDetail, Long> {
    ProgramDetail getBySyllabusProgramId(Long syllabusProgramId);

}
