package kz.syllabus.repository;

import kz.syllabus.entity.Prerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Long> {

}
