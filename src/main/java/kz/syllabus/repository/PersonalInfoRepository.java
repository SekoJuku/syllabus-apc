package kz.syllabus.repository;

import kz.syllabus.entity.PersonalInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    PersonalInfo getPersonalInfoByUserId(Long userId);
}
