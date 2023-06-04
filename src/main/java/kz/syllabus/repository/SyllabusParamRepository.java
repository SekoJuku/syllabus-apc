package kz.syllabus.repository;

import kz.syllabus.entity.syllabus.SyllabusParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SyllabusParamRepository extends JpaRepository<SyllabusParam, Long> {
    Optional<SyllabusParam> findBySyllabusId(Long syllabusId);

    SyllabusParam getSyllabusParamBySyllabusId(Long syllabusId);

    boolean existsBySyllabusId(Long syllabusId);

    boolean existsBySyllabusIdAndIsActive(Long id, boolean isActive);

    List<SyllabusParam> findAllByIsSentToDean(boolean isSentToDean);
}
