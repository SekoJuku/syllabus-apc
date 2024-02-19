package kz.syllabus.service.syllabus;

import jakarta.transaction.Transactional;
import kz.syllabus.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.dto.request.syllabus.SyllabusProgramDtoRequest;
import kz.syllabus.exception.domain.BadRequestException;
import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.mapper.ProgramDetailMapper;
import kz.syllabus.persistence.model.Discipline;
import kz.syllabus.persistence.model.syllabus.Syllabus;
import kz.syllabus.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.persistence.model.syllabus.SyllabusProgram;
import kz.syllabus.persistence.model.user.TestUser;
import kz.syllabus.persistence.repository.DisciplineRepository;
import kz.syllabus.persistence.repository.SyllabusRepository;
import kz.syllabus.persistence.repository.TestInstructorRepository;
import kz.syllabus.persistence.repository.TestUserRepository;
import kz.syllabus.service.user.InstructorService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

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
    private final InstructorService instructorService;
    private final PrerequisiteService prerequisiteService;
    private final PostrequisiteService postrequisiteService;
    private final TestUserRepository testUserRepository;
    private final TestInstructorRepository testInstructorRepository;

    @Transactional
    @SneakyThrows
    public Syllabus create(FullSyllabusDTORequest request, boolean isTest) {

        final var discipline = disciplineRepository.findById(request.getDisciplineId())
                                                   .orElseThrow(() -> new BadRequestException("Discipline is not found!"));

        final var syllabus = this.save(Syllabus.builder()
                                               .discipline(discipline)
                                               .name(String.format("%s, %s", discipline.getName(), request.getYear()))
                                               .credits(discipline.getCredits())
                                               .aim(request.getAim())
                                               .tasks(request.getTasks())
                                               .results(request.getResults())
                                               .methodology(request.getMethodology())
                                               .year(request.getYear())
                                               .competences(request.getCompetencies())
                                               .build());

        if (isTest) {
            final var testuser = testUserRepository.findByIin(request.getIin())
                                                   .orElseGet(TestUser::new);

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
    public Syllabus save(Syllabus syllabus) {
        return repository.save(syllabus);
    }

    @Transactional
    public void deleteById(Long id) {
        final var syllabus = this.getById(id);
        instructorService.deleteAllBySyllabusId(id);
        repository.delete(syllabus);
    }

    public List<Syllabus> checkForInstructors(List<Syllabus> list) {
        list.removeIf(item -> !instructorService.existsBySyllabusId(item.getId()));
        return list;
    }

    @SneakyThrows
    public Boolean checkForFinal(Long syllabusId) {
        final var syllabus = this.getById(syllabusId);
        final var syllabuses =
                checkForInstructors(getAllByDisciplineAndYear(syllabus.getDiscipline(), syllabus.getYear()));
        return syllabuses.stream().distinct().limit(2).count() == 1;
    }

    private List<Syllabus> getAllByDisciplineAndYear(Discipline discipline, String year) {
        return repository.getAllByDisciplineIdAndYear(discipline.getId(), year);
    }

    public List<Discipline> getDisciplines(Long userId) {
        return disciplineService.getAll();
    }

    public List<Syllabus> getSyllabusesByDisciplineAndYear(Long userId, Long disciplineId, String year) {

        return Optional.of(repository.getAllByDisciplineIdAndYear(disciplineId, year))
                       .map(this::checkForInstructors)
                       .orElseGet(List::of);
    }

    public Syllabus getSyllabus(Long userId, Long syllabusId) {
        return getById(syllabusId);
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

        if (discipline.getSyllabuses().isEmpty())
            syllabus.setSyllabusParam(SyllabusParam.newEmptyParam(syllabus));

        final var syllabusPrograms = syllabus.getSyllabusPrograms();
        for (SyllabusProgramDtoRequest item : request.getSyllabusProgram()) {
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

    @SneakyThrows
    public Syllabus getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Syllabus not found"));
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

    public List<Syllabus> findAll() {
        return repository.findAll();
    }
}
