package kz.syllabus.service;

import jakarta.transaction.Transactional;
import kz.syllabus.entity.syllabus.Syllabus;
import kz.syllabus.entity.syllabus.SyllabusParam;
import kz.syllabus.entity.user.User;
import kz.syllabus.service.syllabus.SyllabusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoordinatorService {
    private final SyllabusService syllabusService;

    public List<Syllabus> getSyllabuses(User user) {
        return Optional.of(syllabusService.findAll())
                .map(syllabusService::checkForInstructors)
                .orElseGet(List::of);
    }

    @Transactional
    public SyllabusParam approve(Long id) {
        Syllabus syllabus = syllabusService.getById(id);
        syllabus.getSyllabusParam().setIsApprovedByCoordinator(true);
        syllabus.getSyllabusParam().setIsSentToDean(true);
        return syllabusService.save(syllabus).getSyllabusParam();
    }

    public List<Syllabus> getTestSyllabuses(User user) {
        return Optional.of(syllabusService.findAll())
                .map(syllabusService::checkForTest)
                .orElseGet(List::of);
    }

}
