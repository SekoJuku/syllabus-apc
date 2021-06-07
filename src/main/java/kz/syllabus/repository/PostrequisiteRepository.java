package kz.syllabus.repository;

import kz.syllabus.entity.Postrequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostrequisiteRepository extends JpaRepository<Postrequisite, Long> {

}
