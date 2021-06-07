package kz.syllabus.repository;

import kz.syllabus.entity.DisciplineEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineEvaluationRepository extends JpaRepository<DisciplineEvaluation, Long> {

}
