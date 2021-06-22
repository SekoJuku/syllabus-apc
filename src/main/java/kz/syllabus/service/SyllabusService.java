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

    @Transactional
    public ResponseEntity<?> create(FullSyllabusDTORequest fullSyllabusDTORequest) {
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
                if(item1.getWeek().equals(SyllabusProgram.getWeek())) {
                    ProgramDetail newProgramDetail = new ProgramDetail();
                    if(item1.getId() != null) {
                        newProgramDetail.setId(item1.getId());
                    }
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
                }
            }
        }
        response.setSyllabusProgram(list1);


        response.setProgramDetails(list2);

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



//    @Transactional
//    public ResponseEntity<?> getAll(Integer userId) {
////        List<Integer> list = new ArrayList<>();
////        List<Instructor> instructors = instructorRepository.getByUserId(userId);
////        List<SyllabusDtoResponse> disciplineInfos = new ArrayList<>();
////        for (Instructor item :
////                instructors) {
////            list.add(item.getSyllabusId());
////        }
////        for (Integer item :
////                list) {
////            disciplineInfos.add(SyllabusFacade.objectToDto(syllabusRepository.getById(item)));
////        }
////        return ResponseEntity.ok(disciplineInfos);
//        log.info("Before function");
//        List<MainPageDtoResponse> syllabuses = new ArrayList<>();
//        List<Instructor> asInstructor = instructorRepository.getByUserId(userId);
//        List<Integer> listOfsyllabuses = new ArrayList<>();
//        for (Instructor item :
//                asInstructor) {
//            listOfsyllabuses.add(item.getSyllabusId());
//        }
//        for (Integer item :
//                listOfsyllabuses) {
//            MainPageDtoResponse response = new MainPageDtoResponse();
//            Syllabus syllabus = syllabusRepository.getById(item);
//            response.setId(syllabus.getId());
//            response.setName(syllabus.getName());
//            response.setYear(syllabus.getYear());
//            log.info("Before Discipline " + syllabus.getDisciplineId());
//            Optional<Discipline> optionalDiscipline = disciplineRepository.findById(syllabus.getDisciplineId());
//            Discipline discipline = optionalDiscipline.get();
//            log.info("After Discipline");
//            response.setDiscipline(discipline.getName());
//            Optional<SyllabusParam> optionalSyllabusParam = syllabusParamRepository.findBySyllabusId(syllabus.getId());
//            if(optionalSyllabusParam.isPresent())
//                response.setActive(optionalSyllabusParam.get().getIsActive());
//            else
//                response.setActive(false);
//            List<Instructor> allInstructors = instructorRepository.getBySyllabusId(syllabus.getId());
//            List<MainpageDtoComponent> components = new ArrayList<>();
//            for (Instructor item1 :
//                    allInstructors) {
//                MainpageDtoComponent component = new MainpageDtoComponent();
//                PersonalInfo personalInfo = personalInfoRepository.getPersonalInfoByUserId(item1.getUserId());
//// !!!!!               component.setId(personalInfo.getUserId());
//
//                component.setName(personalInfo.getName());
//                component.setLastname(personalInfo.getSname());
//                components.add(component);
//            }
//            response.setInstructors(components);
//            syllabuses.add(response);
//        }
//        return ResponseEntity.ok(syllabuses);
//
//    }

    @Transactional
    public FullSyllabusDtoResponse getOne(Integer userId, Integer SyllabusId) {
        Syllabus syllabus = syllabusRepository.getById(SyllabusId);
        FullSyllabusDtoResponse response = SyllabusFacade.objectToSyllabusDto(syllabus, new FullSyllabusDtoResponse());

        Discipline discipline = disciplineRepository.getById(syllabus.getDisciplineId());

        response.setUserId(userId);
        response.setLectureHoursPerWeek(discipline.getLectureHoursPerWeek());
        response.setPracticeHoursPerWeek(discipline.getPracticeHoursPerWeek());
        response.setIswHoursPerWeek(discipline.getIswHoursPerWeek());

        List<Postrequisite> postrequisiteList = postrequisiteRepository.findAllByDisciplineId(SyllabusId);
        List<Prerequisite> prerequisiteList = prerequisiteRepository.findAllByDisciplineId(SyllabusId);

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
        List<Syllabus> syllabusList = checkForInstructors(syllabusRepository.findAll());
        log.info("After syllabus repo");
        List<Discipline> allActive = new ArrayList<>();
        for (Syllabus item :
                syllabusList) {
            log.info("Before syllabus param repo");
            Optional<SyllabusParam> optionalSyllabusParam = syllabusParamRepository.findBySyllabusId(item.getId());
            log.info("After syllabus param repo");
            if(optionalSyllabusParam.isPresent()) {
                if(optionalSyllabusParam.get().getIsActive()) {
                    allActive.add(disciplineRepository.getSyllabusById(item.getId()));
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
                }
                if(compareSyllabus == item) {
                    continue;
                }
                if(!SyllabusFacade.checkSimilarity(compareSyllabus,item)) {
                    return ResponseEntity.ok("Not similar");
                } else {
                    SyllabusParam param = primarySyllabus.getSyllabusParam();
                    param.setIsFinal(true);
                    param.setIsSendable(true);
                }
            }
        }
        return ResponseEntity.ok(primarySyllabus);
    }

    public Syllabus getSyllabusById(Integer id) {
        return syllabusRepository.getSyllabusById(id);
    }

    public ResponseEntity<?> getDisciplines(Integer userId) {
        return ResponseEntity.ok(disciplineRepository.findAll());
    }

//    public ResponseEntity<?> getSyllabusesByDiscipleAndYear(Integer userId, Integer disciplineId, String year) {
//        List<FullSyllabusDtoResponse> responses = new ArrayList<>();
//        List<Syllabus> syllabuses = checkForInstructors(syllabusRepository.getAllByDisciplineIdAndYear(disciplineId,year));
//        for (Syllabus item :
//                syllabuses) {
//            List<Instructor> instructors = instructorRepository.getBySyllabusId(item.getId());
//            FullSyllabusDtoResponse response = getOne(userId, item.getId());
//            List<MainpageDtoComponent> components = new ArrayList<>();
//            for (Instructor item1:
//                 instructors) {
//                PersonalInfo info = personalInfoRepository.getPersonalInfoByUserId(item1.getUserId());
//                MainpageDtoComponent component = new MainpageDtoComponent();
//                component.setId(info.getUserId());
//                component.setName(info.getName());
//                component.setLastname(info.getSname());
//                components.add(component);
//            }
//            response.setInstructors(components);
//            responses.add(response);
//        }
//        return ResponseEntity.ok(responses);
//    }

    public ResponseEntity<?> getSyllabus(Integer userId, Integer syllabusId) {
        return ResponseEntity.ok(getOne(userId,syllabusId));
    }

    public List<Syllabus> checkForParam(List<Syllabus> list ) {
        list.removeIf(item -> item.getSyllabusParam() == null);
        return list;
    }

    public List<CoordinatorMainPageDtoResponse> getSyllabusIsSent(Integer userId) {
        List<CoordinatorMainPageDtoResponse> responses = new ArrayList<>();
        List<Syllabus> list = checkForInstructors(syllabusRepository.findAll());
        List<Syllabus> syllabuses = checkForParam(list);
        for (Syllabus item :
                syllabuses) {
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
        Syllabus syllabus = syllabusRepository.getById(id);
        SyllabusParam param = syllabus.getSyllabusParam();
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
        Syllabus syllabus = syllabusRepository.getById(id);
        SyllabusParam param = syllabus.getSyllabusParam();
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

    public List<Discipline> findAll() {
        return disciplineRepository.findAll();
    }

    public Prerequisite findPrerequisiteById(Integer syllabusId) {
        return prerequisiteRepository.findPrerequisiteById(syllabusId);
    }

    public Postrequisite findPostrequisiteById(Integer syllabusId) {
        return postrequisiteRepository.findPostrequisiteById(syllabusId);
    }

    public Discipline getDisciplineById(Integer syllabusIdPrereq) {
        return disciplineRepository.getDisciplineById(syllabusIdPrereq);
    }

    public SyllabusProgram getSyllabusProgramById(Integer syllabusId) {
        return syllabusProgramRepository.getSyllabusProgramById(syllabusId);
    }

    public ProgramDetail getProgramDetailById(Integer syllabusProgramId) {
        return programDetailRepository.getProgramDetailById(syllabusProgramId);
    }
}
