package kz.syllabus.coordinator.service.impl;

import jakarta.transaction.Transactional;
import kz.syllabus.common.exception.domain.BadRequestException;
import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.common.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.common.persistence.model.user.User;
import kz.syllabus.common.persistence.repository.SyllabusRepository;
import kz.syllabus.common.service.syllabus.SyllabusService;
import kz.syllabus.coordinator.service.CoordinatorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoordinatorServiceImpl implements CoordinatorService {

    private final SyllabusService syllabusService;
    private final SyllabusRepository syllabusRepository;

    @Override
    public List<Syllabus> getSyllabuses(User user) {
        return Optional.of(syllabusRepository.findAll())
                       .map(syllabusService::checkForInstructors)
                       .orElseGet(List::of);
    }

    @Override
    @SneakyThrows
    @Transactional
    public SyllabusParam approve(Long id) {
        final var syllabus = syllabusRepository.findById(id)
                                               .orElseThrow(() -> new BadRequestException("Syllabus with given id not found!"));

        syllabus.getSyllabusParam().setIsApprovedByCoordinator(true);
        syllabus.getSyllabusParam().setIsSentToDean(true);

        return syllabusRepository.save(syllabus).getSyllabusParam();
    }

    @Override
    public List<Syllabus> getTestSyllabuses(User user) {
        return Optional.of(syllabusRepository.findAll())
                       .map(syllabusService::checkForTest)
                       .orElseGet(List::of);
    }

}
