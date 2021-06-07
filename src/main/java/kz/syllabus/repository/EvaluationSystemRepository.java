package kz.syllabus.repository;

import kz.syllabus.entity.EvaluationSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationSystemRepository extends JpaRepository<EvaluationSystem, Long> {

}
