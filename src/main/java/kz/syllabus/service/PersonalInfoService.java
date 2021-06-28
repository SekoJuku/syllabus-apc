package kz.syllabus.service;


import kz.syllabus.entity.PersonalInfo;
import kz.syllabus.repository.PersonalInfoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonalInfoService {
    private final PersonalInfoRepository personalInfoRepository;

    public PersonalInfo getPersonalInfoByUserId(Integer userId) {
        return personalInfoRepository.getPersonalInfoByUserId(userId);
    }

    public void registerNewTeacher(PersonalInfo personalInfo) {
        Integer userId = personalInfo.getUser().getId().intValue();
        String name = personalInfo.getName();
        String surname = personalInfo.getSname();
        String middleName = personalInfo.getMname();
        String academicDegree = personalInfo.getAcademicDegree();
        String phone = personalInfo.getPhone();
        String email = personalInfo.getEmail();
        personalInfoRepository.registerNewTeacher(userId, surname,name,middleName,academicDegree,phone,email);
    }
}
