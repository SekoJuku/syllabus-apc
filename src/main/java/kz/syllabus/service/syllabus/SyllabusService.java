package kz.syllabus.service.syllabus;

import jakarta.transaction.Transactional;
import kz.syllabus.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.dto.request.syllabus.SyllabusProgramDtoRequest;
import kz.syllabus.dto.response.syllabus.FullSyllabusDtoResponse;
import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.persistence.SyllabusRepository;
import kz.syllabus.persistence.TestInstructorRepository;
import kz.syllabus.persistence.TestUserRepository;
import kz.syllabus.persistence.model.Discipline;
import kz.syllabus.persistence.model.Postrequisite;
import kz.syllabus.persistence.model.Prerequisite;
import kz.syllabus.persistence.model.ProgramDetail;
import kz.syllabus.persistence.model.syllabus.Syllabus;
import kz.syllabus.persistence.model.syllabus.SyllabusParam;
import kz.syllabus.persistence.model.syllabus.SyllabusProgram;
import kz.syllabus.persistence.model.user.TestUser;
import kz.syllabus.service.user.InstructorService;
import kz.syllabus.util.SyllabusUtil;
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
    private final DisciplineService disciplineService;
    private final SyllabusRepository repository;
    private final SyllabusParamService syllabusParamService;
    private final SyllabusProgramService syllabusProgramService;
    private final ProgramDetailService programDetailService;
    private final InstructorService instructorService;
    private final PrerequisiteService prerequisiteService;
    private final PostrequisiteService postrequisiteService;
    private final TestUserRepository testUserRepository;
    private final TestInstructorRepository testInstructorRepository;
    private final SyllabusUtil syllabusUtil;

    @Transactional
    @SneakyThrows
    public FullSyllabusDtoResponse create(FullSyllabusDTORequest request, boolean isTest) {

        final var discipline = disciplineService.getById(request.getDisciplineId());

        final var syllabus = this.save(
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
                        .build());

        if (isTest) {
            TestUser testuser = testUserRepository.getByIin(request.getIin());
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
            return toResponse(syllabus);
        }

        syllabusParamService.getOrCreate(syllabus);

        return toResponse(repository.save(syllabus));
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

    @SneakyThrows
    public FullSyllabusDtoResponse getOne(Long userId, Long id) {
        final var syllabus = getById(id);
        return toResponse(syllabus);
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

    public List<MainPageDtoResponse> getSyllabusesByDisciplineAndYear(
            Long userId, Long disciplineId, String year) {

        return Optional.of(repository.getAllByDisciplineIdAndYear(disciplineId, year))
                .map(this::checkForInstructors)
                .map(syllabusUtil::toMainPageDtoResponse)
                .orElseGet(List::of);
    }

    public FullSyllabusDtoResponse getSyllabus(Long userId, Long syllabusId) {
        return getOne(userId, syllabusId);
    }

    public List<Syllabus> checkForParam(List<Syllabus> list) {
        return list;
    }

    public Discipline testCreateSyllabus(FullSyllabusDTORequest request) throws NotFoundException {
        final var discipline = disciplineService.getById(request.getDisciplineId());
        final var syllabus =
                Syllabus.builder()
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
            syllabus.setSyllabusParam(SyllabusParam.newEmptyParam());

        final var syllabusPrograms = syllabus.getSyllabusPrograms();
        for (SyllabusProgramDtoRequest item : request.getSyllabusProgram()) {
            final var syllabusProgram = new SyllabusProgram();
            syllabusProgram.setLectureTheme(item.getLectureTheme());
            syllabusProgram.setPracticeTheme(item.getPracticeTheme());
            syllabusProgram.setIswTheme(item.getIswTheme());
            syllabusProgram.setWeek(item.getWeek());
            request.getProgramDetails().stream()
                    .filter(i -> i.getWeek().equals(item.getWeek()))
                    .forEach(
                            i -> {
                                final var programDetail = ProgramDetail.fromRequest(i);
                                syllabusProgram.setProgramDetail(programDetail);
                            });
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

    public List<MainPageDtoResponse> getAllTestSyllabusesByIin(String iin) {
        final var user = testUserRepository.getByIin(iin);
        return Optional.of(repository.findAll())
                .map(this::checkForTest)
                .stream()
                .flatMap(List::stream)
                .filter(item -> testInstructorRepository.existsBySyllabusIdAndUserId(item.getId(), user.getId()))
                .map(syllabusUtil::toMainPageDtoResponse)
                .toList();
    }

    @SneakyThrows
    public FullSyllabusDtoResponse toResponse(Syllabus syllabus) {
        final var response = (FullSyllabusDtoResponse) syllabus.toDto();

        final var discipline = disciplineService.getById(syllabus.getDiscipline().getId());

        response.setLectureHoursPerWeek(discipline.getLectureHoursPerWeek());
        response.setPracticeHoursPerWeek(discipline.getPracticeHoursPerWeek());
        response.setIswHoursPerWeek(discipline.getIswHoursPerWeek());

        final var postrequisiteList = postrequisiteService.getAllBySyllabusId(syllabus.getId());
        final var prerequisiteList = prerequisiteService.getAllBySyllabusId(syllabus.getId());

        response.setPrerequisites(prerequisiteList.stream().map(Prerequisite::getId).toList());
        response.setPostrequisites(postrequisiteList.stream().map(Postrequisite::getId).toList());

        final var syllabusProgramList = syllabusProgramService.getAllBySyllabusId(syllabus.getId());

        response.setSyllabusProgram(syllabusProgramList.stream().map(SyllabusProgram::toDto).toList());

        response.setProgramDetails(
                syllabusProgramList.stream()
                        .map(item -> programDetailService.getBySyllabusId(item.getId()).toDto())
                        .toList());

        return response;
    }

    public List<Syllabus> findAll() {
        return repository.findAll();
    }
}
