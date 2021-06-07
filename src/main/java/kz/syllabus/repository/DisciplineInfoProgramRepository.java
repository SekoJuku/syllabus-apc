package kz.syllabus.repository;

import kz.syllabus.entity.DisciplineInfoProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineInfoProgramRepository extends JpaRepository<DisciplineInfoProgram, Long> {

}
