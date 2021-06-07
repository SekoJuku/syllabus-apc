package kz.syllabus.repository;

import kz.syllabus.entity.ProgramInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramInfoRepository extends JpaRepository<ProgramInfo, Long> {

}
