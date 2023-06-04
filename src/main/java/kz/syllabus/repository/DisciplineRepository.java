package kz.syllabus.repository;

import kz.syllabus.entity.Discipline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

    @Query(value = "select * from disciplines\n" +
            "full join syllabus\n" +
            "on disciplines.id = syllabus.discipline_id\n" +
            "full join evaluation_system\n" +
            "on syllabus.evaluation_id = evaluation_system.id\n" +
            "where syllabus.id = ?;", nativeQuery = true)
    Discipline getSyllabusById(Long id);
}
