package kz.syllabus.service;

import kz.syllabus.entity.*;
import kz.syllabus.repository.*;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.image.RescaleOp;
import java.util.List;
import java.util.Optional;

@Service
@Log
@AllArgsConstructor
public class SyllabusService {
    private final UserService userService;
    private final DisciplineRepository disciplineRepository;
    private final SyllabusRepository syllabusRepository;
    private final SyllabusParamRepository syllabusParamRepository;
    private final SyllabusProgramRepository syllabusProgramRepository;
    private final ProgramInfoRepository programInfoRepository;
    private final ProgramDetailRepository programDetailRepository;
    private final InstructorRepository instructorRepository;
    private final PrerequisiteRepository prerequisiteRepository;
    private final PostrequisiteRepository postrequisiteRepository;

//    public ResponseEntity<?> create(FullSyllabusDTORequest fullSyllabusDTORequest) {
//        FullSyllabusDtoResponse response = new FullSyllabusDtoResponse();
//
//        Discipline discipline = disciplineRepository.getById(fullSyllabusDTORequest.getDisciplineId());
//
//        response.setLectureHoursPerWeek(discipline.getLectureHoursPerWeek());
//        response.setPracticeHoursPerWeek(discipline.getPracticeHoursPerWeek());
//        response.setIswHoursPerWeek(discipline.getIswHoursPerWeek());
//
//        log.info(String.valueOf(fullSyllabusDTORequest));
//        Syllabus newSyllabus = new Syllabus();
//        if(fullSyllabusDTORequest.getId() != null) {
//            newSyllabus.setId(fullSyllabusDTORequest.getId());
//        }
////        newSyllabus.setDisciplineId(fullSyllabusDTORequest.getDisciplineId());
//        newSyllabus.setCredits(fullSyllabusDTORequest.getCredits());
//        newSyllabus.setAim(fullSyllabusDTORequest.getAim());
//        newSyllabus.setTasks(fullSyllabusDTORequest.getTasks());
//        newSyllabus.setResults(fullSyllabusDTORequest.getResults());
//        newSyllabus.setMethodology(fullSyllabusDTORequest.getMethodology());
//        Syllabus syllabus = syllabusRepository.save(newSyllabus);
//        Integer disciplineInfoId = syllabus.getId();
//        log.info(String.valueOf(disciplineInfoId));
//
////        response.setDisciplineId(syllabus.getDisciplineId());
//        response.setCredits(syllabus.getCredits());
//        response.setAim(syllabus.getAim());
//        response.setTasks(syllabus.getTasks());
//        response.setResults(syllabus.getResults());
//        response.setMethodology(syllabus.getMethodology());
//
//        List<Integer> l = new ArrayList<>();
//
//        for (Integer item:
//                fullSyllabusDTORequest.getPrerequisites()){
//            Prerequisite prerequisite = new Prerequisite();
//            prerequisite.setDisciplineId(item);
//            prerequisite.setDisciplineInfoId(disciplineInfoId);
//            Prerequisite newPrerequisite = prerequisiteRepository.save(prerequisite);
//
//            l.add(newPrerequisite.getDisciplineId());
//        }
//        response.setPrerequisites(l);
//
//        List<Integer> l1 = new ArrayList<>();
//
//        for (Integer item :
//                fullSyllabusDTORequest.getPostrequisites()) {
//            Postrequisite postrequisite = new Postrequisite();
//            postrequisite.setDisciplineId(item);
//            postrequisite.setDisciplineInfoId(disciplineInfoId);
//            Postrequisite newPostrequisite = postrequisiteRepository.save(postrequisite);
//            l1.add(newPostrequisite.getDisciplineId());
//        }
//        response.setPostrequisites(l1);
//
//        Instructor newInstructor = new Instructor();
//        newInstructor.setDisciplineInfoId(disciplineInfoId);
//        newInstructor.setUserId(fullSyllabusDTORequest.getUserId());
//        Instructor instructor = instructorRepository.save(newInstructor);
//        Integer instructorId = instructor.getId();
//        log.info(String.valueOf(instructorId));
//
//        response.setUserId(instructor.getUserId());
//
//        SyllabusProgram newSyllabusProgram = new SyllabusProgram();
////        newSyllabusProgram.setInstructorId(instructorId);
////        newSyllabusProgram.setDisciplineInfoId(disciplineInfoId);
////        newSyllabusProgram.setEvaluationId(fullSyllabusDTORequest.getEvaluationId());
////        newSyllabusProgram.setCompetencies(fullSyllabusDTORequest.getCompetencies());
//        SyllabusProgram syllabusProgram = syllabusProgramRepository.save(newSyllabusProgram);
//        Integer disciplineInfoProgramId = syllabusProgram.getId();
//        log.info(String.valueOf(disciplineInfoProgramId));
//
////        response.setEvaluationId(syllabusProgram.getEvaluationId());
////        response.setCompetencies(syllabusProgram.getCompetencies());
//
//        HashMap<Integer, Integer> idMap = new HashMap<>();
//        List<ProgramInfoDtoResponse> list1 = new ArrayList<>();
//        log.info("programInfo");
//        for (ProgramInfoDtoRequest item :
//                fullSyllabusDTORequest.getProgramInfo()) {
//            ProgramInfo newProgramInfo = new ProgramInfo();
//            newProgramInfo.setProgramId(disciplineInfoProgramId);
//            newProgramInfo.setLectureTheme(item.getLectureTheme());
//            newProgramInfo.setPracticeTheme(item.getPracticeTheme());
//            newProgramInfo.setIswTheme(item.getIswTheme());
//            newProgramInfo.setWeek(item.getWeek());
//            ProgramInfo programInfo = programInfoRepository.save(newProgramInfo);
//            Integer id = programInfo.getId();
//            idMap.put(item.getWeek(),id);
//            log.info(String.valueOf(id));
//            list1.add(ProgramInfoFacade.objectToDto(programInfo));
//        }
//        response.setProgramInfo(list1);
//
//        log.info("programDetails");
//        List<ProgramDetailDtoResponse> list2 = new ArrayList<>();
//        for (ProgramDetailDtoRequest item :
//                fullSyllabusDTORequest.getProgramDetails()) {
//            ProgramDetail newProgramDetail = new ProgramDetail();
////            newProgramDetail.setProgramInfoId(idMap.get(item.getWeek()));
//            newProgramDetail.setLectureFof(item.getLectureFof());
//            newProgramDetail.setPracticeFof(item.getPracticeFof());
//            newProgramDetail.setIswFof(item.getIswFof());
//            newProgramDetail.setLectureLiterature(item.getLectureLiterature());
//            newProgramDetail.setPracticeLiterature(item.getPracticeLiterature());
//            newProgramDetail.setIswLiterature(item.getIswLiterature());
//            newProgramDetail.setLectureAssessment(item.getLectureAssessment());
//            newProgramDetail.setPracticeAssessment(item.getPracticeAssessment());
//            newProgramDetail.setIswAssessment(item.getIswAssessment());
//            ProgramDetail programDetail = programDetailRepository.save(newProgramDetail);
//            Integer id = programDetail.getId();
//            log.info(String.valueOf(id));
//            list2.add(ProgramDetailFacade.objectToDto(programDetail));
//        }
//        response.setProgramDetails(list2);
//
////        if(!syllabusRepository.existsByDisciplineId(newSyllabus.getDisciplineId())) {
//            SyllabusParam newSyllabusParam = new SyllabusParam();
//            newSyllabusParam.setSyllabusId(syllabus.getId());
////            newSyllabusParam.setFinal(false);
////            newSyllabusParam.setSendable(false);
////            newSyllabusParam.setApprovedByCoordinator(false);
////            newSyllabusParam.setSentToCoordinator(false);
////            newSyllabusParam.setApprovedByDean(false);
////            newSyllabusParam.setSentToDean(false);
////            newSyllabusParam.setSendable(false);
//            syllabusParamRepository.save(newSyllabusParam);
////        }
////        else {
////            //List<SyllabusDtoResponse> syllabuses = (List<SyllabusDtoResponse>) getAll(fullSyllabusDTORequest.getUserId()).getBody();
////        }
////        return ResponseEntity.ok(response);
////    }
//
//
//
//    public ResponseEntity<?> getAll(Integer userId) {
//        List<Integer> list = new ArrayList<>();
//        List<Instructor> instructors = instructorRepository.getByUserId(userId);
//        List<SyllabusDtoResponse> disciplineInfos = new ArrayList<>();
//        for (Instructor item :
//                instructors) {
//            list.add(item.getDisciplineInfoId());
//        }
//        for (Integer item :
//                list) {
//            disciplineInfos.add(DisciplineInfoFacade.objectToDto(syllabusRepository.getById(item)));
//        }
//        return ResponseEntity.ok(disciplineInfos);
//    }
//
//    public ResponseEntity<?> getOne(Integer userId, Integer disciplineInfoId) {
////        if(!instructorRepository.existsByUserIdAndDisciplineInfoId(userId,disciplineInfoId)) {
////            return ResponseEntity.badRequest().body("Don't exist or you don't have permission!");
////        }
//
//        Syllabus syllabus = syllabusRepository.getById(disciplineInfoId);
//        FullSyllabusDtoResponse response = DisciplineInfoFacade.objectToSyllabusDto(syllabus, new FullSyllabusDtoResponse());
//
////        Discipline discipline = disciplineRepository.getById(syllabus.getDisciplineId());
//
//        response.setUserId(userId);
////        response.setLectureHoursPerWeek(discipline.getLectureHoursPerWeek());
////        response.setPracticeHoursPerWeek(discipline.getPracticeHoursPerWeek());
////        response.setIswHoursPerWeek(discipline.getIswHoursPerWeek());
//
//        SyllabusProgram syllabusProgram = syllabusProgramRepository.getByDisciplineInfoId(disciplineInfoId);
//        response.equals(DisciplineInfoProgramFacade.objectToSyllabusDto(syllabusProgram,response));
//
//        List<Postrequisite> postrequisiteList = postrequisiteRepository.findAllByDisciplineId(disciplineInfoId);
//        List<Prerequisite> prerequisiteList = prerequisiteRepository.findAllByDisciplineId(disciplineInfoId);
//
//        List<Integer> postrequisites = new ArrayList<>();
//        List<Integer> prerequisites = new ArrayList<>();
//
//        for (Postrequisite item :
//                postrequisiteList) {
//            postrequisites.add(item.getDisciplineId());
//        }
//        for (Prerequisite item :
//                prerequisiteList) {
//            prerequisites.add(item.getDisciplineId());
//        }
//        response.setPrerequisites(prerequisites);
//        response.setPostrequisites(postrequisites);
//
//        List<ProgramInfo> programInfoList = programInfoRepository.getAllByProgramId(syllabusProgram.getId());
//
//        List<ProgramInfoDtoResponse> programInfoDtoResponses = new ArrayList<>();
//        List<ProgramDetailDtoResponse> programDetailDtoResponses = new ArrayList<>();
//
//        for (ProgramInfo item :
//                programInfoList) {
//                programInfoDtoResponses.add(ProgramInfoFacade.objectToDto(item));
//                programDetailDtoResponses.add(ProgramDetailFacade.objectToDto(programDetailRepository.getByProgramInfoId(item.getId())));
//        }
//
//        response.setProgramInfo(programInfoDtoResponses);
//        response.setProgramDetails(programDetailDtoResponses);
//
//        return ResponseEntity.ok(response);
//    }
//
//    public ResponseEntity<?> getUserData(Long userId) {
//        return ResponseEntity.ok(userService.findById(userId));
//    }

    public ResponseEntity<?> getSyllabusById(Integer id) {
        return ResponseEntity.ok(disciplineRepository.getSyllabusById(id));
    }
}
