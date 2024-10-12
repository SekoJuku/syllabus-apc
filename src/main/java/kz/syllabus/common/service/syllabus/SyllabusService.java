package kz.syllabus.common.service.syllabus;

import jakarta.transaction.Transactional;
import kz.syllabus.common.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.common.exception.domain.BadRequestException;
import kz.syllabus.common.mapper.ProgramDetailMapper;
import kz.syllabus.common.persistence.model.Discipline;
import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.common.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.common.persistence.model.syllabus.SyllabusProgram;
import kz.syllabus.common.persistence.model.user.TestUser;
import kz.syllabus.common.persistence.repository.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Log
@Service
@AllArgsConstructor
public class SyllabusService {

    private final ProgramDetailMapper programDetailMapper;
    private final DisciplineService disciplineService;
    private final DisciplineRepository disciplineRepository;
    private final SyllabusRepository repository;
    private final SyllabusParamService syllabusParamService;
    private final SyllabusProgramService syllabusProgramService;
    private final PrerequisiteService prerequisiteService;
    private final PostrequisiteService postrequisiteService;
    private final TestUserRepository testUserRepository;
    private final TestInstructorRepository testInstructorRepository;
    private final InstructorRepository instructorRepository;

    @Transactional
    @SneakyThrows
    public Syllabus create(FullSyllabusDTORequest request, boolean isTest) {

        final var discipline = disciplineRepository.findById(request.getDisciplineId())
                                                   .orElseThrow(() -> new BadRequestException("Discipline is not found!"));

        final var syllabus = repository.save(
                Syllabus.builder()
                        .discipline(discipline)
                        .name(String.format("%s, %s", discipline.getName(), request.getYear()))
                        .credits(discipline.getCredits())
                        .aim(request.getAim())
                        .tasks(request.getTasks())
                        .results(request.getResults())
                        .methodology(request.getMethodology())
                        .year(request.getYear())
                        .competences(request.getCompetencies())
                        .build()
        );

        if (isTest) {
            final var testuser = testUserRepository.findByIin(request.getIin())
                                                   .orElseGet(() -> {
                                                       final var testUser = new TestUser();
                                                       testUser.setIin(request.getIin());
                                                       return testUser;
                                                   });

            testuser.setName(request.getName());
            testuser.setSname(request.getSname());
            testuser.setMname(request.getMname());
            testuser.setAcademicDegree(request.getAcademicDegree());
            testuser.setAcademicRank(request.getAcademicRank());
            testuser.setEmail(request.getEmail());
            testuser.setPhone(request.getPhone());
            testuser.setDescription(request.getDescription());

            testUserRepository.save(testuser);
        }

        prerequisiteService.createAll(request.getPrerequisites(), discipline, syllabus.getId());
        postrequisiteService.createAll(request.getPostrequisites(), discipline, syllabus.getId());

        syllabusProgramService.createAll(request.getSyllabusProgram(), request.getProgramDetails(), syllabus);

        if (isTest) {
            return syllabus;
        }

        syllabusParamService.getOrCreate(syllabus);

        return repository.save(syllabus);
    }

    @Transactional
    public void deleteById(Long id) {
        final var syllabus = findById(id);

        instructorRepository.deleteAllBySyllabusId(id);

        repository.delete(syllabus);
    }

    public List<Syllabus> checkForInstructors(List<Syllabus> list) {
        list.removeIf(item -> !instructorRepository.existsBySyllabusId(item.getId()));
        return list;
    }

    @SneakyThrows
    public Boolean checkForFinal(Long syllabusId) {
        final var syllabus = findById(syllabusId);

        final var syllabuses = Optional.of(repository.getAllByDisciplineIdAndYear(syllabus.getId(), syllabus.getYear()))
                                       .map(this::checkForInstructors)
                                       .orElseThrow();

        return syllabuses.stream().distinct().limit(2).count() == 1;
    }

    public List<Discipline> getDisciplines() {
        return disciplineService.getAll();
    }

    public List<Syllabus> getSyllabusesByDisciplineAndYear(Long disciplineId, String year) {

        return Optional.of(repository.getAllByDisciplineIdAndYear(disciplineId, year))
                       .map(this::checkForInstructors)
                       .orElseGet(List::of);
    }

    public List<Syllabus> checkForParam(List<Syllabus> list) {
        return list;
    }

    @SneakyThrows
    public Discipline testCreateSyllabus(FullSyllabusDTORequest request) {

        final var discipline = disciplineRepository.findById(request.getDisciplineId())
                                                   .orElseThrow(() -> new BadRequestException("Discipline not found!"));

        final var syllabus = Syllabus.builder()
                                     .name(discipline.getName() + "," + request.getYear())
                                     .credits(discipline.getCredits())
                                     .aim(request.getAim())
                                     .tasks(request.getTasks())
                                     .results(request.getResults())
                                     .methodology(request.getMethodology())
                                     .year(request.getYear())
                                     .competences(request.getCompetencies())
                                     .build();

        if (discipline.getSyllabuses().isEmpty()) {
            syllabus.setSyllabusParam(SyllabusParam.newEmptyParam(syllabus));
        }

        final var syllabusPrograms = syllabus.getSyllabusPrograms();

        for (var item : request.getSyllabusProgram()) {

            final var syllabusProgram = new SyllabusProgram();

            syllabusProgram.setLectureTheme(item.getLectureTheme());
            syllabusProgram.setPracticeTheme(item.getPracticeTheme());
            syllabusProgram.setIswTheme(item.getIswTheme());
            syllabusProgram.setWeek(item.getWeek());

            request.getProgramDetails().stream()
                   .filter(i -> i.getWeek().equals(item.getWeek()))
                   .findFirst()
                   .map(programDetailMapper::map)
                   .ifPresent(syllabusProgram::setProgramDetail);

            syllabusPrograms.add(syllabusProgram);
        }

        syllabus.setSyllabusPrograms(syllabusPrograms);

        return disciplineService.addSyllabus(discipline, syllabus);
    }

    public List<Syllabus> checkForTest(List<Syllabus> list) {
        list.removeIf(item -> !testInstructorRepository.existsBySyllabusId(item.getId()));
        return list;
    }

    @SneakyThrows
    public List<Syllabus> getAllTestSyllabusesByIin(String iin) {
        final var user = testUserRepository.findByIin(iin)
                                           .orElseThrow(() -> new BadRequestException("Iin is invalid"));

        return Optional.of(repository.findAll())
                       .map(this::checkForTest)
                       .stream()
                       .flatMap(List::stream)
                       .filter(item -> testInstructorRepository.existsBySyllabusIdAndUserId(item.getId(), user.getId()))
                       .toList();
    }

    public Syllabus findById(Long syllabusId) {
        return repository.findById(syllabusId)
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }
}
