package kz.syllabus.common.persistence.repository;

import kz.syllabus.common.persistence.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

}
