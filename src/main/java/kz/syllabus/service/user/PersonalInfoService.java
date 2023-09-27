package kz.syllabus.service.user;

import kz.syllabus.persistence.PersonalInfoRepository;
import kz.syllabus.persistence.model.PersonalInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonalInfoService {
    private PersonalInfoRepository repository;

    public PersonalInfo getByUserId(Long userId) {
        return repository.getPersonalInfoByUserId(userId);
    }
}
