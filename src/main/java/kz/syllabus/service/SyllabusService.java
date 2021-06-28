package kz.syllabus.service;

import kz.syllabus.dto.requestDto.FullSyllabusDTORequest;
import kz.syllabus.dto.requestDto.ProgramDetailDtoRequest;
import kz.syllabus.dto.requestDto.SyllabusProgramDtoRequest;
import kz.syllabus.dto.responseDto.*;
import kz.syllabus.entity.*;
import kz.syllabus.repository.*;

import kz.syllabus.utils.facade.ProgramDetailFacade;
import kz.syllabus.utils.facade.SyllabusFacade;
import kz.syllabus.utils.facade.SyllabusProgramFacade;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Log

@Transactional@AllArgsConstructor
public class SyllabusService {
    private final UserService userService;
    private final DisciplineRepository disciplineRepository;
    private final SyllabusRepository syllabusRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final SyllabusParamRepository syllabusParamRepository;
    private final SyllabusProgramRepository syllabusProgramRepository;
    private final ProgramDetailRepository programDetailRepository;
    private final InstructorRepository instructorRepository;
    private final PrerequisiteRepository prerequisiteRepository;
    private final PostrequisiteRepository postrequisiteRepository;
    private final TestUserRepository testUserRepository;
    private final TestInstructorRepository testInstructorRepository;

    @Transactional
    public ResponseEntity<?> create(FullSyllabusDTORequest fullSyllabusDTORequest, boolean isTest) {
        FullSyllabusDtoResponse response = new FullSyllabusDtoResponse();

        Discipline discipline = disciplineRepository.getById(fullSyllabusDTORequest.getDisciplineId());

        response.setLectureHoursPerWeek(discipline.getLectureHoursPerWeek());
        response.setPracticeHoursPerWeek(discipline.getPracticeHoursPerWeek());
        response.setIswHoursPerWeek(discipline.getIswHoursPerWeek());

        log.info(String.valueOf(fullSyllabusDTORequest));
        Syllabus newSyllabus = new Syllabus();
        if(fullSyllabusDTORequest.getId() != null) {
            newSyllabus.setId(fullSyllabusDTORequest.getId());
        }
        newSyllabus.setDisciplineId(fullSyllabusDTORequest.getDisciplineId());
        newSyllabus.setName(discipline.getName()+"," + fullSyllabusDTORequest.getYear());
        newSyllabus.setCredits(discipline.getCredits());
        newSyllabus.setAim(fullSyllabusDTORequest.getAim());
        newSyllabus.setTasks(fullSyllabusDTORequest.getTasks());
        newSyllabus.setResults(fullSyllabusDTORequest.getResults());
        newSyllabus.setMethodology(fullSyllabusDTORequest.getMethodology());
        newSyllabus.setEvaluationId(fullSyllabusDTORequest.getEvaluationId());
        newSyllabus.setYear(fullSyllabusDTORequest.getYear());
        newSyllabus.setCompetences(fullSyllabusDTORequest.getCompetencies());
        newSyllabus.setRubricId(fullSyllabusDTORequest.getRubricId());
        Syllabus syllabus = syllabusRepository.save(newSyllabus);
        Integer syllabusId = syllabus.getId();
        log.info(String.valueOf(syllabusId));

        response.setId(syllabusId);
        response.setName(syllabus.getName());
        response.setDisciplineId(syllabus.getDisciplineId());
        response.setCredits(syllabus.getCredits());
        response.setAim(syllabus.getAim());
        response.setTasks(syllabus.getTasks());
        response.setResults(syllabus.getResults());
        response.setMethodology(syllabus.getMethodology());

        response.setEvaluationId(syllabus.getEvaluationId());
        response.setYear(syllabus.getYear());
        response.setCompetencies(syllabus.getCompetences());
        response.setRubricId(syllabus.getRubricId());


        if(isTest) {
            TestUser testuser = testUserRepository.getById(fullSyllabusDTORequest.getUserId());
            testuser.setIin(fullSyllabusDTORequest.getIin());
            testuser.setName(fullSyllabusDTORequest.getName());
            testuser.setSname(fullSyllabusDTORequest.getSname());
            testuser.setMname(fullSyllabusDTORequest.getMname());
            testuser.setAcademicDegree(fullSyllabusDTORequest.getAcademicDegree());
            testuser.setAcademicRank(fullSyllabusDTORequest.getAcademicRank());
            testuser.setEmail(fullSyllabusDTORequest.getEmail());
            testuser.setPhone(fullSyllabusDTORequest.getPhone());
            testuser.setDescription(fullSyllabusDTORequest.getDescription());
            testUserRepository.save(testuser);
        }


        List<Integer> l = new ArrayList<>();

        for (Integer item:
                fullSyllabusDTORequest.getPrerequisites()){
            if(!prerequisiteRepository.existsByDisciplineIdAndSyllabusId(item,syllabusId)) {
                Prerequisite prerequisite = new Prerequisite();
                prerequisite.setDisciplineId(item);
                prerequisite.setSyllabusId(syllabusId);
                Prerequisite newPrerequisite = prerequisiteRepository.save(prerequisite);
            }
            l.add(item);
        }
        response.setPrerequisites(l);

        List<Integer> l1 = new ArrayList<>();

        for (Integer item :
                fullSyllabusDTORequest.getPostrequisites()) {
            if(!postrequisiteRepository.existsByDisciplineIdAndSyllabusId(item,syllabusId)) {
                Postrequisite postrequisite = new Postrequisite();
                postrequisite.setDisciplineId(item);
                postrequisite.setSyllabusId(syllabusId);
                Postrequisite newPostrequisite = postrequisiteRepository.save(postrequisite);
            }
            l1.add(item);
        }
        response.setPostrequisites(l1);
        if(!isTest) {
            Instructor instructor = new Instructor();
            if(!instructorRepository.existsByUserIdAndSyllabusId(fullSyllabusDTORequest.getUserId(), syllabusId)) {
                Instructor newInstructor = new Instructor();
                newInstructor.setSyllabusId(syllabusId);
                newInstructor.setUserId(fullSyllabusDTORequest.getUserId());
                instructor = instructorRepository.save(newInstructor);
                Integer instructorId = instructor.getId();
                log.info(String.valueOf(instructorId));
            } else {
                instructor = instructorRepository.getInstructorByUserIdAndSyllabusId(fullSyllabusDTORequest.getUserId(), syllabusId);
            }

            response.setUserId(instructor.getUserId());
        }
        else {
            TestInstructor instructor = new TestInstructor();
            if(!testInstructorRepository.existsBySyllabusIdAndUserId(fullSyllabusDTORequest.getUserId(), syllabusId)) {
                TestInstructor newInstructor = new TestInstructor();
                newInstructor.setSyllabusId(syllabusId);
                newInstructor.setUserId(fullSyllabusDTORequest.getUserId());
                instructor = testInstructorRepository.save(newInstructor);
                log.info(String.valueOf(instructor.getId()));
            } else {
                instructor = testInstructorRepository.getBySyllabusIdAndUserId(fullSyllabusDTORequest.getUserId(), syllabusId);
            }

            response.setUserId(instructor.getUserId());
        }

//        SyllabusProgram newSyllabusProgram = new SyllabusProgram();
//        SyllabusProgram syllabusProgram = syllabusProgramRepository.save(newSyllabusProgram);
//        Integer syllabusProgramId = syllabusProgram.getId();
//        log.info(String.valueOf(syllabusProgramId));

        List<SyllabusProgramDtoResponse> list1 = new ArrayList<>();
        log.info("SyllabusProgram");

        log.info("programDetails");
        List<ProgramDetailDtoResponse> list2 = new ArrayList<>();
        for (SyllabusProgramDtoRequest item :
                fullSyllabusDTORequest.getSyllabusProgram()) {
            SyllabusProgram newSyllabusProgram = new SyllabusProgram();
            if(item.getId() != null) {
                newSyllabusProgram.setId(item.getId());
            }
            newSyllabusProgram.setSyllabusId(syllabusId);
            newSyllabusProgram.setLectureTheme(item.getLectureTheme());
            newSyllabusProgram.setPracticeTheme(item.getPracticeTheme());
            newSyllabusProgram.setIswTheme(item.getIswTheme());
            newSyllabusProgram.setWeek(item.getWeek());
            SyllabusProgram SyllabusProgram = syllabusProgramRepository.save(newSyllabusProgram);
            Integer id = SyllabusProgram.getId();
            log.info(String.valueOf(id));
            list1.add(SyllabusProgramFacade.objectToDto(SyllabusProgram));
            for (ProgramDetailDtoRequest item1 :
                    fullSyllabusDTORequest.getProgramDetails()) {
                if(item1.getWeek().equals(item.getWeek())) {
                    ProgramDetail newProgramDetail = new ProgramDetail();
                    if(item1.getId() != null) {
                        newProgramDetail.setId(item1.getId());
                    }
                    try {
                        newProgramDetail.setSyllabusProgramId(id);
                        newProgramDetail.setLectureFof(item1.getLectureFof());
                        newProgramDetail.setPracticeFof(item1.getPracticeFof());
                        newProgramDetail.setIswFof(item1.getIswFof());
                        newProgramDetail.setLectureLiterature(item1.getLectureLiterature());
                        newProgramDetail.setPracticeLiterature(item1.getPracticeLiterature());
                        newProgramDetail.setIswLiterature(item1.getIswLiterature());
                        newProgramDetail.setLectureAssessment(item1.getLectureAssessment());
                        newProgramDetail.setPracticeAssessment(item1.getPracticeAssessment());
                        newProgramDetail.setIswAssessment(item1.getIswAssessment());
                        ProgramDetail programDetail = programDetailRepository.save(newProgramDetail);
                        Integer id1 = programDetail.getId();
                        log.info(String.valueOf(id1));
                        list2.add(ProgramDetailFacade.objectToDto(programDetail));
                    } catch (Exception e) {
                        log.info("Program detail is not working!" + item.getWeek());
                    }
                }
            }
        }
        response.setSyllabusProgram(list1);


        response.setProgramDetails(list2);
        if(isTest) {
            return ResponseEntity.ok(response);
        }
        List<Syllabus> syllabusList = syllabusRepository.getAllByDisciplineIdAndYear(syllabus.getDisciplineId(), syllabus.getYear());
        if(syllabusList.size() != 0) {
            boolean u = false;
            for (Syllabus item :
                    syllabusList) {
                if(instructorRepository.existsBySyllabusId(item.getId()) && !item.getId().equals(syllabusId)) {
                    u = true;
                    break;
                }
            }
            log.info(String.valueOf(u));
            if(!u) {
                SyllabusParam newSyllabusParam = new SyllabusParam();
                newSyllabusParam.setSyllabusId(syllabus.getId());
                newSyllabusParam.setIsFinal(false);
                newSyllabusParam.setIsSendable(false);
                newSyllabusParam.setIsApprovedByCoordinator(false);
                newSyllabusParam.setIsSentToCoordinator(false);
                newSyllabusParam.setIsApprovedByDean(false);
                newSyllabusParam.setIsSentToDean(false);
                newSyllabusParam.setIsSendable(false);
                newSyllabusParam.setIsActive(false);
                syllabus.setSyllabusParam(newSyllabusParam);
                syllabusRepository.save(syllabus);
            }
        }
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<?> deleteSyllabusById(Integer id) {
        Syllabus syllabus = syllabusRepository.getById(id);
        Integer i = 1;
        List<Instructor> instructors = instructorRepository.getBySyllabusId(id);
        for (Instructor item :
                instructors) {
            instructorRepository.deleteById(item.getId());
        }
        Discipline discipline = disciplineRepository.getById(syllabus.getDisciplineId());
        List<Syllabus> syllabusList = discipline.getSyllabuses();
        syllabusList.remove(syllabus);
        discipline.setSyllabuses(syllabusList);
        disciplineRepository.save(discipline);
        //        for (SyllabusProgram item :
//                syllabus.getSyllabusProgram()) {
//            log.info(String.valueOf(i));
//            log.info(item.toString());
//            log.info("Before deletion of program detail");
//            log.info(item.getProgramDetail().toString());
//            ProgramDetail programDetail = programDetailRepository.getProgramDetailBySyllabusProgramId(item.getProgramDetail().getId());
//            programDetailRepository.deleteById(programDetail.getId());
//            log.info("After deletion of program detail, Before syllabus program");
//            syllabusProgramRepository.deleteById(item.getId());
//            log.info("After deletion of syllabus program");
//            i++;
//        }
//        log.info("Before deletion of syllabus");
//        syllabusRepository.deleteById(syllabus.getId());
//        log.info("After deletion");
        return ResponseEntity.ok("Deleted!");
    }



    @Transactional
    public ResponseEntity<?> getAll(Integer userId) {
//        List<Integer> list = new ArrayList<>();
//        List<Instructor> instructors = instructorRepository.getByUserId(userId);
//        List<SyllabusDtoResponse> disciplineInfos = new ArrayList<>();
//        for (Instructor item :
//                instructors) {
//            list.add(item.getSyllabusId());
//        }
//        for (Integer item :
//                list) {
//            disciplineInfos.add(SyllabusFacade.objectToDto(syllabusRepository.getById(item)));
//        }
//        return ResponseEntity.ok(disciplineInfos);
        log.info("Before function");
        List<MainPageDtoResponse> syllabuses = new ArrayList<>();
        List<Instructor> asInstructor = instructorRepository.getByUserId(userId);
        List<Integer> listOfsyllabuses = new ArrayList<>();
        for (Instructor item :
                asInstructor) {
            listOfsyllabuses.add(item.getSyllabusId());
        }
        for (Integer item :
                listOfsyllabuses) {
            MainPageDtoResponse response = new MainPageDtoResponse();
            Syllabus syllabus = syllabusRepository.getById(item);
            response.setId(syllabus.getId());
            response.setName(syllabus.getName());
            response.setYear(syllabus.getYear());
            log.info("Before Discipline " + syllabus.getDisciplineId());
            Optional<Discipline> optionalDiscipline = disciplineRepository.findById(syllabus.getDisciplineId());
            Discipline discipline = optionalDiscipline.get();
            log.info("After Discipline");
            response.setDiscipline(discipline.getName());
            Optional<SyllabusParam> optionalSyllabusParam = syllabusParamRepository.findBySyllabusId(syllabus.getId());
            if(optionalSyllabusParam.isPresent())
                response.setActive(optionalSyllabusParam.get().getIsActive());
            else
                response.setActive(false);
            List<Instructor> allInstructors = instructorRepository.getBySyllabusId(syllabus.getId());
            List<MainpageDtoComponent> components = new ArrayList<>();
            for (Instructor item1 :
                    allInstructors) {
                MainpageDtoComponent component = new MainpageDtoComponent();
                PersonalInfo personalInfo = personalInfoRepository.getPersonalInfoByUserId(item1.getUserId());
                component.setId(personalInfo.getUserId());
                component.setName(personalInfo.getName());
                component.setLastname(personalInfo.getSname());
                components.add(component);
            }
            response.setInstructors(components);
            syllabuses.add(response);
        }
        return ResponseEntity.ok(syllabuses);

    }

    @Transactional
    public FullSyllabusDtoResponse getOne(Integer userId, Integer SyllabusId) {
        Syllabus syllabus = syllabusRepository.getById(SyllabusId);
        FullSyllabusDtoResponse response = SyllabusFacade.objectToSyllabusDto(syllabus, new FullSyllabusDtoResponse());

        Discipline discipline = disciplineRepository.getById(syllabus.getDisciplineId());

        response.setUserId(userId);
        response.setName(syllabus.getName());
        response.setYear(syllabus.getYear());
        response.setCompetencies(syllabus.getCompetences());
        response.setDisciplineId(syllabus.getDisciplineId());
        response.setRubricId(syllabus.getRubricId());
        response.setEvaluationId(syllabus.getEvaluationId());
        response.setLectureHoursPerWeek(discipline.getLectureHoursPerWeek());
        response.setPracticeHoursPerWeek(discipline.getPracticeHoursPerWeek());
        response.setIswHoursPerWeek(discipline.getIswHoursPerWeek());

        List<Postrequisite> postrequisiteList = postrequisiteRepository.getAllBySyllabusId(SyllabusId);
        List<Prerequisite> prerequisiteList = prerequisiteRepository.getAllBySyllabusId(SyllabusId);

        List<Integer> postrequisites = new ArrayList<>();
        List<Integer> prerequisites = new ArrayList<>();

        for (Postrequisite item :
                postrequisiteList) {
            postrequisites.add(item.getDisciplineId());
        }
        for (Prerequisite item :
                prerequisiteList) {
            prerequisites.add(item.getDisciplineId());
        }
        response.setPrerequisites(prerequisites);
        response.setPostrequisites(postrequisites);

        List<SyllabusProgram> SyllabusProgramList = syllabusProgramRepository.getAllBySyllabusId(syllabus.getId());

        List<SyllabusProgramDtoResponse> SyllabusProgramDtoResponses = new ArrayList<>();
        List<ProgramDetailDtoResponse> programDetailDtoResponses = new ArrayList<>();

        for (SyllabusProgram item :
                SyllabusProgramList) {
                SyllabusProgramDtoResponses.add(SyllabusProgramFacade.objectToDto(item));
                programDetailDtoResponses.add(ProgramDetailFacade.objectToDto(programDetailRepository.getBySyllabusProgramId(item.getId())));
        }

        response.setSyllabusProgram(SyllabusProgramDtoResponses);
        response.setProgramDetails(programDetailDtoResponses);

        return response;
    }

    @Transactional
    public ResponseEntity<?> getUserData(Integer userId) {
        return ResponseEntity.ok(personalInfoRepository.getPersonalInfoByUserId(userId));
    }

    @Transactional
    public ResponseEntity<?> getAllFull() {
        log.info("Before func");
        List<Syllabus> list = checkForInstructors(syllabusRepository.findAll());
        List<Syllabus> syllabusList = checkForParam(list);
        log.info("After syllabus repo");
        List<Syllabus> allActive = new ArrayList<>();
        if(!syllabusList.isEmpty()) {
            for (Syllabus item :
                    syllabusList) {
                if(syllabusParamRepository.existsBySyllabusIdAndIsActive(item.getId(), true)) {
                    allActive.add(syllabusRepository.getbyIdSyllabus(item.getId()));
                }
            }
        }
        return ResponseEntity.ok(allActive);
    }

    @Transactional
    public List<Syllabus> checkForInstructors(List<Syllabus> list) {
        list.removeIf(item -> !instructorRepository.existsBySyllabusId(item.getId()));
        return list;
    }


    @Transactional
    public ResponseEntity<?> checkForFinal(Integer syllabusId) {
        Syllabus syllabus = syllabusRepository.getById(syllabusId);
        List<Syllabus> syllabuses = checkForInstructors(syllabusRepository.getAllByDisciplineIdAndYear(syllabus.getDisciplineId(), syllabus.getYear()));
        Syllabus primarySyllabus = new Syllabus();
        SyllabusParam primaryParam = new SyllabusParam();
        log.info("After param");

        if(syllabuses.size() == 1) {
            SyllabusParam syllabusParam = syllabusParamRepository.getSyllabusParamBySyllabusId(syllabusId);
            syllabusParam.setIsFinal(true);
            syllabusParam.setIsSendable(true);
            syllabusParamRepository.save(syllabusParam);
            primarySyllabus = syllabus;
            primarySyllabus.setSyllabusParam(syllabusParam);
        } else {
            Syllabus compareSyllabus = syllabuses.get(0);
            for (Syllabus item :
                    syllabuses) {
                log.info(item.toString());
                if(syllabusParamRepository.existsBySyllabusId(item.getId())) {
                    primarySyllabus = item;
                    primaryParam = syllabusParamRepository.getSyllabusParamBySyllabusId(item.getId());
                }
                if(compareSyllabus == item) {
                    continue;
                }
                if(!SyllabusFacade.checkSimilarity(compareSyllabus,item)) {
                    return ResponseEntity.ok("Not similar");
                } else {
                    SyllabusParam param = primarySyllabus.getSyllabusParam();
                    primaryParam.setIsFinal(true);
                    primaryParam.setIsSendable(true);
                    syllabusParamRepository.save(primaryParam);
                }
            }
        }
        return ResponseEntity.ok(primarySyllabus);
    }

    @Transactional
    public ResponseEntity<?> getSyllabusById(Integer id) {
        return ResponseEntity.ok(syllabusRepository.getbyIdSyllabus(id));
    }

    public ResponseEntity<?> getDisciplines(Integer userId) {
        return ResponseEntity.ok(disciplineRepository.findAll());
    }

    public ResponseEntity<?> getSyllabusesByDiscipleAndYear(Integer userId, Integer disciplineId, String year) {
        List<FullSyllabusDtoResponse> responses = new ArrayList<>();
        List<Syllabus> syllabuses = checkForInstructors(syllabusRepository.getAllByDisciplineIdAndYear(disciplineId,year));
        for (Syllabus item :
                syllabuses) {
            List<Instructor> instructors = instructorRepository.getBySyllabusId(item.getId());
            FullSyllabusDtoResponse response = getOne(userId, item.getId());
            List<MainpageDtoComponent> components = new ArrayList<>();
            for (Instructor item1:
                 instructors) {
                PersonalInfo info = personalInfoRepository.getPersonalInfoByUserId(item1.getUserId());
                MainpageDtoComponent component = new MainpageDtoComponent();
                component.setId(info.getUserId());
                component.setName(info.getName());
                component.setLastname(info.getSname());
                components.add(component);
            }
            response.setInstructors(components);
            responses.add(response);
        }
        return ResponseEntity.ok(responses);
    }

    public ResponseEntity<?> getSyllabus(Integer userId, Integer syllabusId) {
        return ResponseEntity.ok(getOne(userId,syllabusId));
    }

    public List<Syllabus> checkForParam(List<Syllabus> list ) {
        list.removeIf(item -> !syllabusParamRepository.existsBySyllabusId(item.getId()));
        return list;
    }

    public List<CoordinatorMainPageDtoResponse> getSyllabusIsSent(Integer userId) {
        List<CoordinatorMainPageDtoResponse> responses = new ArrayList<>();
        List<Syllabus> list = checkForInstructors(syllabusRepository.findAll());
        List<Syllabus> syllabuses = checkForParam(list);
        for (Syllabus item :
                syllabuses) {
            SyllabusParam param = syllabusParamRepository.getSyllabusParamBySyllabusId(item.getId());
            if(!param.getIsSentToCoordinator())
                continue;

            CoordinatorMainPageDtoResponse response = new CoordinatorMainPageDtoResponse();
            response.setId(item.getId());
            response.setName(item.getName());
            response.setYear(item.getYear());
            Discipline discipline = disciplineRepository.getById(item.getDisciplineId());
            response.setDisciplineName(discipline.getName());
            List<Instructor> instructors = instructorRepository.getBySyllabusId(item.getId());
            List<MainpageDtoComponent> components = new ArrayList<>();
            for (Instructor item1 :
                    instructors) {
                MainpageDtoComponent component = new MainpageDtoComponent();
                component.setId(item1.getUserId());
                PersonalInfo info = personalInfoRepository.getPersonalInfoByUserId(item1.getUserId());
                component.setName(info.getName());
                component.setLastname(info.getSname());
                components.add(component);
            }
            response.setInstructors(components);
            responses.add(response);
        }
        return responses;
    }

    public ResponseEntity<?> approvedSyllabusById(Integer id) {
        SyllabusParam param = syllabusParamRepository.getSyllabusParamBySyllabusId(id);
        param.setIsApprovedByCoordinator(true);
        param.setIsSentToDean(true);
        SyllabusParam newParam = syllabusParamRepository.save(param);
        return ResponseEntity.ok(newParam);
    }

//    public ResponseEntity<?> getSyllabusIsSentToDean(Integer userId) {
//        List<Syllabus> syllabuses = checkForParam(checkForInstructors(syllabusRepository.findAll()));
//
//    }

    public ResponseEntity<?> approvedByDeanById(Integer id) {
        SyllabusParam param = syllabusParamRepository.getSyllabusParamBySyllabusId(id);
        param.setIsApprovedByDean(true);
        param.setIsActive(true);
        SyllabusParam newParam = syllabusParamRepository.save(param);
        return ResponseEntity.ok(newParam);
    }

    public ResponseEntity<?> getSyllabusIsSentToCoordinator(Integer userId) {
        return ResponseEntity.ok(getSyllabusIsSent(userId));
    }

    public ResponseEntity<?> get() {
        Syllabus syllabus = syllabusRepository.getById(11);
        SyllabusParam newSyllabusParam = new SyllabusParam();
        newSyllabusParam.setSyllabusId(syllabus.getId());
        newSyllabusParam.setIsFinal(false);
        newSyllabusParam.setIsSendable(false);
        newSyllabusParam.setIsApprovedByCoordinator(false);
        newSyllabusParam.setIsSentToCoordinator(false);
        newSyllabusParam.setIsApprovedByDean(false);
        newSyllabusParam.setIsSentToDean(false);
        newSyllabusParam.setIsSendable(false);
        newSyllabusParam.setIsActive(false);
        syllabus.setSyllabusParam(newSyllabusParam);
        syllabusRepository.save(syllabus);
        return ResponseEntity.ok(syllabus);
    }

    public ResponseEntity<?> testCreateSyllabus(FullSyllabusDTORequest request) {
        Discipline discipline = disciplineRepository.getById(request.getDisciplineId());
        List<Syllabus> syllabuses = new ArrayList<>();
        if(discipline.getSyllabuses() != null) {
            syllabuses = discipline.getSyllabuses();
        }
        Syllabus syllabus = new Syllabus();
        syllabus.setName(discipline.getName()+"," + request.getYear());
        syllabus.setCredits(discipline.getCredits());
        syllabus.setAim(request.getAim());
        syllabus.setTasks(request.getTasks());
        syllabus.setResults(request.getResults());
        syllabus.setMethodology(request.getMethodology());
        syllabus.setEvaluationId(request.getEvaluationId());
        syllabus.setYear(request.getYear());
        syllabus.setCompetences(request.getCompetencies());
        syllabus.setRubricId(request.getRubricId());
        if(syllabuses.size() == 0) {
            SyllabusParam param = new SyllabusParam();
            param.setIsFinal(false);
            param.setIsSendable(false);
            param.setIsSentToCoordinator(false);
            param.setIsApprovedByCoordinator(false);
            param.setIsSentToDean(false);
            param.setIsApprovedByDean(false);
            param.setIsActive(false);
            syllabus.setSyllabusParam(param);
        }
        List<SyllabusProgram> syllabusPrograms = new ArrayList<>();
        if(syllabus.getSyllabusProgram() != null) {
            syllabusPrograms = syllabus.getSyllabusProgram();
        }
        for (SyllabusProgramDtoRequest item :
                request.getSyllabusProgram()) {
            SyllabusProgram syllabusProgram = new SyllabusProgram();
            syllabusProgram.setLectureTheme(item.getLectureTheme());
            syllabusProgram.setPracticeTheme(item.getPracticeTheme());
            syllabusProgram.setIswTheme(item.getIswTheme());
            syllabusProgram.setWeek(item.getWeek());
            for (ProgramDetailDtoRequest item1 :
                request.getProgramDetails()) {
                if(item1.getWeek().equals(item.getWeek())) {
                    ProgramDetail detail = new ProgramDetail();
                    detail.setLectureFof(item1.getLectureFof());
                    detail.setPracticeFof(item1.getPracticeFof());
                    detail.setIswFof(item1.getIswFof());
                    detail.setLectureLiterature(item1.getLectureLiterature());
                    detail.setPracticeLiterature(item1.getPracticeLiterature());
                    detail.setIswLiterature(item1.getIswLiterature());
                    detail.setLectureAssessment(item1.getLectureAssessment());
                    detail.setPracticeAssessment(item1.getPracticeAssessment());
                    detail.setIswAssessment(item1.getIswAssessment());
                    syllabusProgram.setProgramDetail(detail);
                }
            }
            syllabusPrograms.add(syllabusProgram);
        }
        syllabus.setSyllabusProgram(syllabusPrograms);
        syllabuses.add(syllabus);
        discipline.setSyllabuses(syllabuses);
        Discipline newDiscipline = disciplineRepository.save(discipline);
        return ResponseEntity.ok(newDiscipline);
    }

    public ResponseEntity<?> getFullSyllabus(Integer id) {
        Optional<Syllabus> optionalSyllabus = syllabusRepository.findById(id);

        if(optionalSyllabus.isPresent()){
            Syllabus syllabus = optionalSyllabus.get();
            return ResponseEntity.ok(syllabus);
        }
        return ResponseEntity.badRequest().body("Object by this id not found!");
    }

    public ResponseEntity<?> getSyllabusIsSentToDean(Integer userId) {
        List<CoordinatorMainPageDtoResponse> responses = new ArrayList<>();
        List<Syllabus> syllabusList = checkForParam(checkForInstructors(syllabusRepository.findAll()));
        for (Syllabus item :
                syllabusList) {
            SyllabusParam param = syllabusParamRepository.getSyllabusParamBySyllabusId(item.getId());
            if(!param.getIsSentToDean()) {
                continue;
            }
            CoordinatorMainPageDtoResponse response = new CoordinatorMainPageDtoResponse();
            response.setId(item.getId());
            response.setName(item.getName());
            response.setYear(item.getYear());
            Discipline discipline = disciplineRepository.getById(item.getDisciplineId());
            response.setDisciplineName(discipline.getName());
            List<Instructor> instructors = instructorRepository.getBySyllabusId(item.getId());
            List<MainpageDtoComponent> components = new ArrayList<>();
            for (Instructor item1 :
                    instructors) {
                MainpageDtoComponent component = new MainpageDtoComponent();
                component.setId(item1.getUserId());
                PersonalInfo info = personalInfoRepository.getPersonalInfoByUserId(item1.getUserId());
                component.setName(info.getName());
                component.setLastname(info.getSname());
                components.add(component);
            }
            response.setInstructors(components);
            responses.add(response);
        }
        return ResponseEntity.ok(responses);
    }

    public ResponseEntity<?> sendToCoordinator(Integer syllabusId) {
        SyllabusParam param = syllabusParamRepository.getSyllabusParamBySyllabusId(syllabusId);
        param.setIsSentToCoordinator(true);
        SyllabusParam newParam = syllabusParamRepository.save(param);
        return ResponseEntity.ok(newParam);
    }

    public List<Syllabus> checkForTest(List<Syllabus> list) {
        list.removeIf(item -> !testInstructorRepository.existsBySyllabusId(item.getId()));
        return list;
    }

    public ResponseEntity<?> getAllTest(Integer userId) {
        List<CoordinatorMainPageDtoResponse> responses = new ArrayList<>();
        List<Syllabus> syllabuses = checkForTest(syllabusRepository.findAll());
        for (Syllabus item :
                syllabuses) {
            CoordinatorMainPageDtoResponse response = new CoordinatorMainPageDtoResponse();
            response.setId(item.getId());
            response.setName(item.getName());
            response.setYear(item.getYear());
            Discipline discipline = disciplineRepository.getById(item.getDisciplineId());
            response.setDisciplineName(discipline.getName());
            List<MainpageDtoComponent> components = new ArrayList<>();
            MainpageDtoComponent component = new MainpageDtoComponent();
            TestUser user = testUserRepository.getById(userId);
            component.setId(userId);
            component.setName(user.getName());
            component.setLastname(user.getSname());
            components.add(component);
            response.setInstructors(components);
            responses.add(response);
        }
        return ResponseEntity.ok(responses);
    }
}
