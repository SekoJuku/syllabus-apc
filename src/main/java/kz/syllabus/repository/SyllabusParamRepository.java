package kz.syllabus.repository;

import kz.syllabus.entity.SyllabusParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyllabusParamRepository extends JpaRepository<SyllabusParam, Integer> {

}
