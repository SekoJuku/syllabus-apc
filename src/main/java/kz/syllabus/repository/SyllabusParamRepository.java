package kz.syllabus.repository;

import kz.syllabus.entity.SyllabusParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SyllabusParamRepository extends JpaRepository<SyllabusParam, Integer> {
    Optional<SyllabusParam> findBySyllabusId(Integer syllabusId);
    SyllabusParam getSyllabusParamBySyllabusId(Integer syllabusId);
    boolean existsBySyllabusId(Integer syllabusId);
}
