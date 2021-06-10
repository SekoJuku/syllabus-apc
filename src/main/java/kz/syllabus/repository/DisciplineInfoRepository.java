package kz.syllabus.repository;

import kz.syllabus.entity.DisciplineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineInfoRepository extends JpaRepository<DisciplineInfo, Integer> {

}
