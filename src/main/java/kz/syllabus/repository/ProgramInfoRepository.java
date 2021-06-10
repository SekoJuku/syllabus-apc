package kz.syllabus.repository;

import kz.syllabus.entity.ProgramInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramInfoRepository extends JpaRepository<ProgramInfo, Integer> {
    List<ProgramInfo> getAllByProgram_id(Integer programId);
}
