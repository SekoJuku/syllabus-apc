package kz.syllabus.coordinator.service;

import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.common.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.common.persistence.model.user.User;

import java.util.List;

public interface CoordinatorService {
    List<Syllabus> getSyllabuses(User user);

    SyllabusParam approve(Long id);

    List<Syllabus> getTestSyllabuses(User user);
}
