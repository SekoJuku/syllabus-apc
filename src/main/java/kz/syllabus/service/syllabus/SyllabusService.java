package kz.syllabus.service.syllabus;

import jakarta.transaction.Transactional;

import kz.syllabus.dto.request.syllabus.FullSyllabusDTORequest;
import kz.syllabus.dto.request.syllabus.SyllabusProgramDtoRequest;
import kz.syllabus.dto.response.syllabus.CoordinatorMainPageDtoResponse;
import kz.syllabus.dto.response.syllabus.FullSyllabusDtoResponse;
import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.dto.response.syllabus.MainpageDtoComponent;
import kz.syllabus.entity.*;
import kz.syllabus.entity.syllabus.Syllabus;
import kz.syllabus.entity.syllabus.SyllabusParam;
import kz.syllabus.entity.syllabus.SyllabusProgram;
import kz.syllabus.entity.user.Instructor;
import kz.syllabus.entity.user.TestUser;
import kz.syllabus.exception.domain.NotFoundException;
import kz.syllabus.repository.*;
import kz.syllabus.service.*;
import kz.syllabus.service.user.PersonalInfoService;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log
@Service
@AllArgsConstructor
public class SyllabusService {
    private final DisciplineService disciplineService;
    private final SyllabusRepository repository;
    private final PersonalInfoService personalInfoService;
    private final SyllabusParamService syllabusParamService;
    private final SyllabusProgramService syllabusProgramService;
    private final ProgramDetailService programDetailService;
    private final InstructorService instructorService;
    private final PrerequisiteService prerequisiteService;
    private final PostrequisiteService postrequisiteService;
    private final TestUserRepository testUserRepository;
    private final TestInstructorRepository testInstructorRepository;

    @Transactional
    public FullSyllabusDtoResponse create(FullSyllabusDTORequest request, boolean isTest)
            throws NotFoundException {
        Discipline discipline = disciplineService.getById(request.getDisciplineId());

        Syllabus syllabus = this.save(
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
        syllabusParamService.getOrCreateSyllabusParam(syllabus);

        return toResponse(repository.save(syllabus));
    }

    @Transactional
    public Syllabus save(Syllabus syllabus) {
        return repository.save(syllabus);
    }

    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        Syllabus syllabus = this.getById(id);
        instructorService.deleteAllBySyllabusId(id);
        repository.delete(syllabus);
    }

    public List<MainPageDtoResponse> getAll(Long userId) {
        List<Instructor> instructors = instructorService.getByUserId(userId);

        return instructors.stream().map(instructor -> this.toMainPageDtoResponse(instructor.getSyllabus().getId()))
                .toList();
    }

    @SneakyThrows
    public FullSyllabusDtoResponse getOne(Long userId, Long id) {
        Syllabus syllabus = getById(id);
        return toResponse(syllabus);
    }

    public PersonalInfo getUserData(Long userId) {
        return personalInfoService.getByUserId(userId);
    }

    public List<Syllabus> getAllFull() {
        List<Syllabus> list = checkForInstructors(repository.findAll());
        List<Syllabus> syllabusList = checkForParam(list);

        return syllabusList.stream()
                .filter(item -> syllabusParamService.existsBySyllabusIdAndIsActive(item.getId(),
                        true))
                .toList();
    }

    public List<Syllabus> checkForInstructors(List<Syllabus> list) {
        list.removeIf(item -> !instructorService.existsBySyllabusId(item.getId()));
        return list;
    }

    @SneakyThrows
    public Boolean checkForFinal(Long syllabusId) {
        Syllabus syllabus = this.getById(syllabusId);
        List<Syllabus> syllabuses =
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
        List<Syllabus> syllabuses =
                checkForInstructors(
                        repository.getAllByDisciplineIdAndYear(disciplineId, year));

        return syllabuses.stream().map(item -> this.toMainPageDtoResponse(item.getId())).toList();
    }

    public FullSyllabusDtoResponse getSyllabus(Long userId, Long syllabusId) {
        return getOne(userId, syllabusId);
    }

    public List<Syllabus> checkForParam(List<Syllabus> list) {
        return list;
    }

    public List<CoordinatorMainPageDtoResponse> getSyllabusIsSent(Long userId) {
        List<Syllabus> list = checkForInstructors(repository.findAll());
        List<Syllabus> syllabuses = checkForParam(list);
        return syllabuses.stream().map(this::toCoordinatorMainpageDtoResponse).toList();
    }

    @SneakyThrows
    public SyllabusParam approvedSyllabusById(Long id) {
        Syllabus syllabus = this.getById(id);
        syllabus.getSyllabusParam().setIsApprovedByCoordinator(true);
        syllabus.getSyllabusParam().setIsSentToDean(true);
        return this.save(syllabus).getSyllabusParam();
    }

    @SneakyThrows
    public SyllabusParam approvedByDeanById(Long id) {
        Syllabus syllabus = this.getById(id);
        syllabus.getSyllabusParam().setIsApprovedByDean(true);
        syllabus.getSyllabusParam().setIsActive(true);
        return this.save(syllabus).getSyllabusParam();
    }

    public List<CoordinatorMainPageDtoResponse> getSyllabusIsSentToCoordinator(Long userId) {
        return getSyllabusIsSent(userId);
    }

    public Discipline testCreateSyllabus(FullSyllabusDTORequest request) throws NotFoundException {
        Discipline discipline = disciplineService.getById(request.getDisciplineId());
        Syllabus syllabus =
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

        List<SyllabusProgram> syllabusPrograms = syllabus.getSyllabusPrograms();
        for (SyllabusProgramDtoRequest item : request.getSyllabusProgram()) {
            SyllabusProgram syllabusProgram = new SyllabusProgram();
            syllabusProgram.setLectureTheme(item.getLectureTheme());
            syllabusProgram.setPracticeTheme(item.getPracticeTheme());
            syllabusProgram.setIswTheme(item.getIswTheme());
            syllabusProgram.setWeek(item.getWeek());
            request.getProgramDetails().stream()
                    .filter(i -> i.getWeek().equals(item.getWeek()))
                    .forEach(
                            i -> {
                                ProgramDetail programDetail = ProgramDetail.fromRequest(i);
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

    public List<CoordinatorMainPageDtoResponse> getSyllabusIsSentToDean(Long userId) {
        var params = syllabusParamService.getAllSentToDean();

        return params.stream().map(item -> this.toCoordinatorMainpageDtoResponse(item.getSyllabus()))
                .toList();
    }

    public SyllabusParam sendToCoordinator(Long syllabusId) {
        return syllabusParamService.setSentToCoordinator(syllabusId);
    }

    public List<Syllabus> checkForTest(List<Syllabus> list) {
        list.removeIf(item -> !testInstructorRepository.existsBySyllabusId(item.getId()));
        return list;
    }

    public List<CoordinatorMainPageDtoResponse> getAllTest(Long userId) {
        List<CoordinatorMainPageDtoResponse> responses = new ArrayList<>();
        List<Syllabus> syllabuses = checkForTest(repository.findAll());
        for (Syllabus item : syllabuses) {
            CoordinatorMainPageDtoResponse response = toCoordinatorMainpageDtoResponse(item);
            responses.add(response);
        }
        return responses;
    }

    public List<CoordinatorMainPageDtoResponse> getAllTestSyllabusesByIIN(String iin) {
        List<CoordinatorMainPageDtoResponse> responses = new ArrayList<>();
        List<Syllabus> syllabuses = checkForTest(repository.findAll());
        TestUser user = testUserRepository.getByIin(iin);
        for (Syllabus item : syllabuses) {
            if (testInstructorRepository.existsBySyllabusIdAndUserId(item.getId(), user.getId())) {
                CoordinatorMainPageDtoResponse response = toCoordinatorMainpageDtoResponse(item);
                responses.add(response);
            }
        }
        return responses;
    }

    public FullSyllabusDtoResponse toResponse(Syllabus syllabus) throws NotFoundException {
        FullSyllabusDtoResponse response = (FullSyllabusDtoResponse) syllabus.toDto();

        Discipline discipline = disciplineService.getById(syllabus.getDiscipline().getId());

        response.setLectureHoursPerWeek(discipline.getLectureHoursPerWeek());
        response.setPracticeHoursPerWeek(discipline.getPracticeHoursPerWeek());
        response.setIswHoursPerWeek(discipline.getIswHoursPerWeek());

        List<Postrequisite> postrequisiteList =
                postrequisiteService.getAllBySyllabusId(syllabus.getId());
        List<Prerequisite> prerequisiteList =
                prerequisiteService.getAllBySyllabusId(syllabus.getId());

        response.setPrerequisites(prerequisiteList.stream().map(Prerequisite::getId).toList());
        response.setPostrequisites(postrequisiteList.stream().map(Postrequisite::getId).toList());

        List<SyllabusProgram> syllabusProgramList =
                syllabusProgramService.getAllBySyllabusId(syllabus.getId());

        response.setSyllabusProgram(
                syllabusProgramList.stream().map(SyllabusProgram::toDto).toList());

        response.setProgramDetails(
                syllabusProgramList.stream()
                        .map(item -> programDetailService.getBySyllabusId(item.getId()).toDto())
                        .toList());

        return response;
    }

    @SneakyThrows
    private CoordinatorMainPageDtoResponse toCoordinatorMainpageDtoResponse(Syllabus item) {
        CoordinatorMainPageDtoResponse response = new CoordinatorMainPageDtoResponse();
        response.setId(item.getId());
        response.setName(item.getName());
        response.setYear(item.getYear());
        Discipline discipline = disciplineService.getById(item.getDiscipline().getId());
        response.setDisciplineName(discipline.getName());
        List<Instructor> instructors = instructorService.getBySyllabusId(item.getId());
        response.setInstructors(instructors.stream().map(i -> {
            var info =  personalInfoService.getByUserId(i.getUserId());
            return MainpageDtoComponent.builder()
                    .id(info.getUser().getId())
                    .name(info.getName())
                    .lastname(info.getSname())
                    .build();
            }).toList());
        return response;
    }


    @SneakyThrows
    public MainPageDtoResponse toMainPageDtoResponse(Long syllabusId) {
        Syllabus syllabus = this.getById(syllabusId);
        return MainPageDtoResponse.builder()
                .id(syllabus.getId())
                .name(syllabus.getName())
                .year(syllabus.getYear())
                .discipline(syllabus.getDiscipline().getName())
                .isActive(syllabus.getSyllabusParam().getIsActive())
                .instructors(syllabus.getInstructors().stream().map(i -> {
                    var info =  personalInfoService.getByUserId(i.getUserId());
                    return MainpageDtoComponent.builder()
                            .id(info.getUser().getId())
                            .name(info.getName())
                            .lastname(info.getSname())
                            .build();
                }).toList())
                .build();
    }
}
