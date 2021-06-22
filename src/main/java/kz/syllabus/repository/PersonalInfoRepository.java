package kz.syllabus.repository;

import kz.syllabus.entity.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Integer> {
    @Query(value = "SELECT * FROM personal_info where user_id = ?;", nativeQuery = true)
    PersonalInfo getPersonalInfoByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO personal_info(user_id, sname,name,mname,academic_degree,phone,email) VALUES(:user_id, :sname,:name,:mname,:academic_degree,:phone,:email);", nativeQuery = true)
    void registerNewTeacher(@Param("user_id") Integer userId,@Param("sname") String surname,@Param("name") String name,@Param("mname") String middleName,
                            @Param("academic_degree") String academicDegree,@Param("phone") String phone,@Param("email") String email);
}
