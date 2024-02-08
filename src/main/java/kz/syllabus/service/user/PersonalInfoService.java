package kz.syllabus.service.user;

import kz.syllabus.persistence.model.PersonalInfo;
import kz.syllabus.persistence.repository.PersonalInfoRepository;
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
