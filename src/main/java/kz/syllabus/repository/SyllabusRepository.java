package kz.syllabus.repository;

import kz.syllabus.entity.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Integer> {
    boolean existsByDisciplineId(Integer disciplineId);
    List<Syllabus> getAllByDisciplineIdAndYear(Integer disciplineId, String year);
    @Query(value = "select s  from Syllabus s WHERE syllabus.id = ?1", nativeQuery = true)
    Syllabus getSyllabusOnlyById(Integer id);

    @Query(value = "select * from syllabus\n" +
            "full join disciplines\n" +
            "on disciplines.id = syllabus.discipline_id\n" +
            "where syllabus.id = ?;", nativeQuery = true)
    Syllabus getbyIdSyllabus(Integer id);
//    @Query(value = "select * from syllabus\n" +
//            "full join syllabus_program\n" +
//            "on syllabus.id = syllabus_program.syllabus_id\n" +
//            "where syllabus.id = ?;", nativeQuery = true)
//    Syllabus getSyllabus(Integer syllabusId);
//    boolean existsByDisciplineId(Integer disciplineId);
}
