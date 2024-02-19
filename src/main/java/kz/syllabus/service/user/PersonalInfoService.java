package kz.syllabus.service.user;

import kz.syllabus.persistence.model.PersonalInfo;
import kz.syllabus.persistence.model.user.Instructor;
import kz.syllabus.persistence.model.user.User;
import kz.syllabus.persistence.repository.PersonalInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonalInfoService {
    private PersonalInfoRepository repository;

    public PersonalInfo getByUserId(Long userId) {
        return repository.getPersonalInfoByUserId(userId);
    }

    public PersonalInfo get(Instructor instructor) {

        return Optional.of(instructor)
                       .map(Instructor::getUser)
                       .map(User::getPersonalInfo)
                       .orElseThrow(() -> new RuntimeException("Something went wrong during getting personal info of instructor"));
    }
}
