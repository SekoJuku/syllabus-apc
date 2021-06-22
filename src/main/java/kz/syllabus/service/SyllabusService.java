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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Log
@AllArgsConstructor
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
        newSyllabus.setName(fullSyllabusDTORequest.getName());
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
            Prerequisite prerequisite = new Prerequisite();
            prerequisite.setDisciplineId(item);
            prerequisite.setSyllabusId(syllabusId);
            Prerequisite newPrerequisite = prerequisiteRepository.save(prerequisite);

            l.add(newPrerequisite.getDisciplineId());
        }
        response.setPrerequisites(l);

        List<Integer> l1 = new ArrayList<>();

        for (Integer item :
                fullSyllabusDTORequest.getPostrequisites()) {
            Postrequisite postrequisite = new Postrequisite();
            postrequisite.setDisciplineId(item);
            postrequisite.setSyllabusId(syllabusId);
            Postrequisite newPostrequisite = postrequisiteRepository.save(postrequisite);
            l1.add(newPostrequisite.getDisciplineId());
        }
        response.setPostrequisites(l1);

        Instructor newInstructor = new Instructor();
        newInstructor.setSyllabusId(syllabusId);
        newInstructor.setUserId(fullSyllabusDTORequest.getUserId());
        Instructor instructor = instructorRepository.save(newInstructor);
        Integer instructorId = instructor.getId();
        log.info(String.valueOf(instructorId));

        response.setUserId(instructor.getUserId());

//        SyllabusProgram newSyllabusProgram = new SyllabusProgram();
//        SyllabusProgram syllabusProgram = syllabusProgramRepository.save(newSyllabusProgram);
//        Integer syllabusProgramId = syllabusProgram.getId();
//        log.info(String.valueOf(syllabusProgramId));

        HashMap<Integer, Integer> idMap = new HashMap<>();
        List<SyllabusProgramDtoResponse> list1 = new ArrayList<>();
        log.info("SyllabusProgram");
        for (SyllabusProgramDtoRequest item :
                fullSyllabusDTORequest.getSyllabusProgram()) {
            SyllabusProgram newSyllabusProgram = new SyllabusProgram();
            newSyllabusProgram.setSyllabusId(syllabusId);
            newSyllabusProgram.setLectureTheme(item.getLectureTheme());
            newSyllabusProgram.setPracticeTheme(item.getPracticeTheme());
            newSyllabusProgram.setIswTheme(item.getIswTheme());
            newSyllabusProgram.setWeek(item.getWeek());
            SyllabusProgram SyllabusProgram = syllabusProgramRepository.save(newSyllabusProgram);
            Integer id = SyllabusProgram.getId();
            idMap.put(item.getWeek(),id);
            log.info(String.valueOf(id));
            list1.add(SyllabusProgramFacade.objectToDto(SyllabusProgram));
        }
        response.setSyllabusProgram(list1);

        log.info("programDetails");
        List<ProgramDetailDtoResponse> list2 = new ArrayList<>();
        for (ProgramDetailDtoRequest item :
                fullSyllabusDTORequest.getProgramDetails()) {
            ProgramDetail newProgramDetail = new ProgramDetail();
            newProgramDetail.setSyllabusProgramId(idMap.get(item.getWeek()));
            newProgramDetail.setLectureFof(item.getLectureFof());
            newProgramDetail.setPracticeFof(item.getPracticeFof());
            newProgramDetail.setIswFof(item.getIswFof());
            newProgramDetail.setLectureLiterature(item.getLectureLiterature());
            newProgramDetail.setPracticeLiterature(item.getPracticeLiterature());
            newProgramDetail.setIswLiterature(item.getIswLiterature());
            newProgramDetail.setLectureAssessment(item.getLectureAssessment());
            newProgramDetail.setPracticeAssessment(item.getPracticeAssessment());
            newProgramDetail.setIswAssessment(item.getIswAssessment());
            ProgramDetail programDetail = programDetailRepository.save(newProgramDetail);
            Integer id = programDetail.getId();
            log.info(String.valueOf(id));
            list2.add(ProgramDetailFacade.objectToDto(programDetail));
        }
        response.setProgramDetails(list2);


        if(!syllabusRepository.existsByDisciplineId(newSyllabus.getDisciplineId())) {
            SyllabusParam newSyllabusParam = new SyllabusParam();
            newSyllabusParam.setSyllabusId(syllabus.getId());
            newSyllabusParam.setIsFinal(false);
            newSyllabusParam.setIsSendable(false);
            newSyllabusParam.setIsApprovedByCoordinator(false);
            newSyllabusParam.setIsSentToCoordinator(false);
            newSyllabusParam.setIsApprovedByDean(false);
            newSyllabusParam.setIsSentToDean(false);
            newSyllabusParam.setIsSendable(false);
            syllabusParamRepository.save(newSyllabusParam);
        }
        return ResponseEntity.ok(response);
    }



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

    public ResponseEntity<?> getOne(Integer userId, Integer SyllabusId) {
        if(!instructorRepository.existsByUserIdAndSyllabusId(userId,SyllabusId)) {
            return ResponseEntity.badRequest().body("Don't exist or you don't have permission!");
        }

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

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getUserData(Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    public ResponseEntity<?> getAllFull() {
        log.info("Before func");
        List<Syllabus> syllabusList = syllabusRepository.findAll();
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
    public ResponseEntity<?> checkForFinal(Integer syllabusId) {
        Syllabus syllabus = syllabusRepository.getById(syllabusId);
        List<Syllabus> syllabuses = syllabusRepository.getAllByDisciplineIdAndYear(syllabus.getDisciplineId(), syllabus.getYear());
        Syllabus primarySyllabus = new Syllabus();
        if(syllabuses.size() == 1) {
            SyllabusParam syllabusParam = syllabusParamRepository.getSyllabusParamBySyllabusId(syllabusId);
            syllabusParam.setIsFinal(true);
        } else {
            for (Syllabus item :
                    syllabuses) {
                log.info(item.toString());
                if(syllabusParamRepository.existsBySyllabusId(item.getId())) {
                    primarySyllabus = item;
                }
            }
        }
        return ResponseEntity.ok(primarySyllabus);
    }

    public ResponseEntity<?> getSyllabusById(Integer id) {
        return ResponseEntity.ok(disciplineRepository.getSyllabusById(id));
    }
}
