package kz.syllabus.dean.service.impl;

import kz.syllabus.common.exception.domain.BadRequestException;
import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.common.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.common.persistence.repository.SyllabusParamRepository;
import kz.syllabus.common.persistence.repository.SyllabusRepository;
import kz.syllabus.dean.service.DeanService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class DeanServiceImpl implements DeanService {

    private final SyllabusRepository syllabusRepository;
    private final SyllabusParamRepository syllabusParamRepository;

    @Override
    public List<Syllabus> getAll() {

        return syllabusParamRepository.findAllByIsSentToDean(true)
                                      .stream()
                                      .map(SyllabusParam::getSyllabus)
                                      .toList();
    }

    @Override
    @SneakyThrows
    public SyllabusParam approve(Long id) {

        final var syllabus = syllabusRepository.findById(id)
                                               .orElseThrow(() -> new BadRequestException("Syllabus with given id is not found!"));

        syllabus.getSyllabusParam().setIsApprovedByDean(true);
        syllabus.getSyllabusParam().setIsActive(true);

        return syllabusRepository.save(syllabus).getSyllabusParam();
    }
}
