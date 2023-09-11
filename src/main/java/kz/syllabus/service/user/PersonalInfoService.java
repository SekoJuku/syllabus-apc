package kz.syllabus.service.user;

import kz.syllabus.entity.PersonalInfo;
import kz.syllabus.repository.user.PersonalInfoRepository;

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
