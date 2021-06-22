package kz.syllabus.repository;

import kz.syllabus.entity.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Integer> {
    PersonalInfo getPersonalInfoByUserId(Integer userId);
}
