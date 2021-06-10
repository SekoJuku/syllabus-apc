package kz.syllabus.repository;

import kz.syllabus.entity.Instructor;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>{


    @Query(value = "select discipline_id, name " +
            "from discipline_info " +
            "inner join disciplines on discipline_info.discipline_id = disciplines.id " +
            "join instructors on discipline_info.id = instructors.discipline_info_id " +
            "where instructors.id = ?", nativeQuery = true)
    Collection<Instructor> getSyllabusesByInstructor(Integer id);
}
