package kz.syllabus.repository;


import kz.syllabus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users(username, password, role_id) VALUES(:username, :password, :role_id)", nativeQuery = true)
    void registerNewTeacher(@Param("username") String username,@Param("password") String password, @Param("role_id") Integer roleId);
}
