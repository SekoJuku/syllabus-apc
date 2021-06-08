package kz.syllabus.service;

import kz.syllabus.dto.requestDto.ProgramDetailDtoRequest;
import kz.syllabus.dto.requestDto.ProgramInfoDtoRequest;
import kz.syllabus.dto.requestDto.SyllabusDTORequest;
import kz.syllabus.dto.responseDto.DisciplineInfoDtoResponse;
import kz.syllabus.dto.responseDto.ProgramDetailDtoResponse;
import kz.syllabus.dto.responseDto.ProgramInfoDtoResponse;
import kz.syllabus.dto.responseDto.SyllabusDtoResponse;
import kz.syllabus.entity.*;
import kz.syllabus.repository.*;
import kz.syllabus.utils.facade.DisciplineInfoFacade;
import kz.syllabus.utils.facade.DisciplineInfoProgramFacade;
import kz.syllabus.utils.facade.ProgramDetailFacade;
import kz.syllabus.utils.facade.ProgramInfoFacade;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Log
@AllArgsConstructor
public class SyllabusService {
    private final UserService userService;
    private final DisciplineRepository disciplineRepository;
    private final DisciplineInfoRepository disciplineInfoRepository;
    private final DisciplineInfoProgramRepository disciplineInfoProgramRepository;
    private final ProgramInfoRepository programInfoRepository;
    private final ProgramDetailRepository programDetailRepository;
    private final InstructorRepository instructorRepository;
    private final PrerequisiteRepository prerequisiteRepository;
    private final PostrequisiteRepository postrequisiteRepository;

    public ResponseEntity<?> create(SyllabusDTORequest syllabusDTORequest) {
        SyllabusDtoResponse response = new SyllabusDtoResponse();

        Discipline discipline = disciplineRepository.getById(syllabusDTORequest.getDisciplineId());

        response.setLectureHoursPerWeek(discipline.getLectureHoursPerWeek());
        response.setPracticeHoursPerWeek(discipline.getPracticeHoursPerWeek());
        response.setIswHoursPerWeek(discipline.getIswHoursPerWeek());

        log.info(String.valueOf(syllabusDTORequest));
        DisciplineInfo newDisciplineInfo = new DisciplineInfo();
        if(syllabusDTORequest.getId() != null) {
            newDisciplineInfo.setId(syllabusDTORequest.getId());
        }
        newDisciplineInfo.setDisciplineId(syllabusDTORequest.getDisciplineId());
        newDisciplineInfo.setCredits(syllabusDTORequest.getCredits());
        newDisciplineInfo.setAim(syllabusDTORequest.getAim());
        newDisciplineInfo.setTasks(syllabusDTORequest.getTasks());
        newDisciplineInfo.setResults(syllabusDTORequest.getResults());
        newDisciplineInfo.setMethodology(syllabusDTORequest.getMethodology());
        DisciplineInfo disciplineInfo = disciplineInfoRepository.save(newDisciplineInfo);
        Integer disciplineInfoId = disciplineInfo.getId();
        log.info(String.valueOf(disciplineInfoId));

        response.setDisciplineId(disciplineInfo.getDisciplineId());
        response.setCredits(disciplineInfo.getCredits());
        response.setAim(disciplineInfo.getAim());
        response.setTasks(disciplineInfo.getTasks());
        response.setResults(disciplineInfo.getResults());
        response.setMethodology(disciplineInfo.getMethodology());

        List<Integer> l = new ArrayList<>();

        for (Integer item:
                syllabusDTORequest.getPrerequisites()){
            Prerequisite prerequisite = new Prerequisite();
            prerequisite.setDisciplineId(item);
            prerequisite.setDisciplineInfoId(disciplineInfoId);
            Prerequisite newPrerequisite = prerequisiteRepository.save(prerequisite);

            l.add(newPrerequisite.getDisciplineId());
        }
        response.setPrerequisites(l);

        List<Integer> l1 = new ArrayList<>();

        for (Integer item :
                syllabusDTORequest.getPostrequisites()) {
            Postrequisite postrequisite = new Postrequisite();
            postrequisite.setDisciplineId(item);
            postrequisite.setDisciplineInfoId(disciplineInfoId);
            Postrequisite newPostrequisite = postrequisiteRepository.save(postrequisite);
            l1.add(newPostrequisite.getDisciplineId());
        }
        response.setPostrequisites(l1);

        Instructor newInstructor = new Instructor();
        newInstructor.setDisciplineInfoId(disciplineInfoId);
        newInstructor.setUserId(syllabusDTORequest.getUserId());
        Instructor instructor = instructorRepository.save(newInstructor);
        Integer instructorId = instructor.getId();
        log.info(String.valueOf(instructorId));

        response.setUserId(instructor.getUserId());

        DisciplineInfoProgram newDisciplineInfoProgram = new DisciplineInfoProgram();
        newDisciplineInfoProgram.setInstructorId(instructorId);
        newDisciplineInfoProgram.setDisciplineInfoId(disciplineInfoId);
        newDisciplineInfoProgram.setEvaluationId(syllabusDTORequest.getEvaluationId());
        newDisciplineInfoProgram.setCompetencies(syllabusDTORequest.getCompetencies());
        DisciplineInfoProgram disciplineInfoProgram = disciplineInfoProgramRepository.save(newDisciplineInfoProgram);
        Integer disciplineInfoProgramId = disciplineInfoProgram.getId();
        log.info(String.valueOf(disciplineInfoProgramId));

        response.setEvaluationId(disciplineInfoProgram.getEvaluationId());
        response.setCompetencies(disciplineInfoProgram.getCompetencies());

        HashMap<Integer, Integer> idMap = new HashMap<>();
        List<ProgramInfoDtoResponse> list1 = new ArrayList<>();
        log.info("programInfo");
        for (ProgramInfoDtoRequest item :
                syllabusDTORequest.getProgramInfo()) {
            ProgramInfo newProgramInfo = new ProgramInfo();
            newProgramInfo.setProgram_id(disciplineInfoProgramId);
            newProgramInfo.setLectureTheme(item.getLectureTheme());
            newProgramInfo.setPracticeTheme(item.getPracticeTheme());
            newProgramInfo.setIswTheme(item.getIswTheme());
            newProgramInfo.setWeek(item.getWeek());
            ProgramInfo programInfo = programInfoRepository.save(newProgramInfo);
            Integer id = programInfo.getId();
            idMap.put(item.getWeek(),id);
            log.info(String.valueOf(id));
            list1.add(ProgramInfoFacade.objectToDto(programInfo));
        }
        response.setProgramInfo(list1);

        log.info("programDetails");
        List<ProgramDetailDtoResponse> list2 = new ArrayList<>();
        for (ProgramDetailDtoRequest item :
                syllabusDTORequest.getProgramDetails()) {
            ProgramDetail newProgramDetail = new ProgramDetail();
            newProgramDetail.setProgramInfoId(idMap.get(item.getWeek()));
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

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getAll(Integer userId) {
        List<Integer> list = new ArrayList<>();
        List<Instructor> instructors = instructorRepository.getByUserId(userId);
        List<DisciplineInfoDtoResponse> disciplineInfos = new ArrayList<>();
        for (Instructor item :
                instructors) {
            list.add(item.getDisciplineInfoId());
        }
        for (Integer item :
                list) {
            disciplineInfos.add(DisciplineInfoFacade.objectToDto(disciplineInfoRepository.getById(item)));
        }
        return ResponseEntity.ok(disciplineInfos);
    }

    public ResponseEntity<?> getOne(Integer userId, Integer disciplineInfoId) {
        if(!instructorRepository.existsByUserIdAndDisciplineInfoId(userId,disciplineInfoId)) {
            return ResponseEntity.badRequest().body("Don't exist or you don't have permission!");
        }

        DisciplineInfo disciplineInfo = disciplineInfoRepository.getById(disciplineInfoId);
        SyllabusDtoResponse response = DisciplineInfoFacade.objectToSyllabusDto(disciplineInfo, new SyllabusDtoResponse());

        Discipline discipline = disciplineRepository.getById(disciplineInfo.getDisciplineId());

        response.setUserId(userId);
        response.setLectureHoursPerWeek(discipline.getLectureHoursPerWeek());
        response.setPracticeHoursPerWeek(discipline.getPracticeHoursPerWeek());
        response.setIswHoursPerWeek(discipline.getIswHoursPerWeek());

        DisciplineInfoProgram disciplineInfoProgram = disciplineInfoProgramRepository.getByDisciplineInfoId(disciplineInfoId);
        response.equals(DisciplineInfoProgramFacade.objectToSyllabusDto(disciplineInfoProgram,response));

        List<Postrequisite> postrequisiteList = postrequisiteRepository.findAllByDisciplineId(disciplineInfoId);
        List<Prerequisite> prerequisiteList = prerequisiteRepository.findAllByDisciplineId(disciplineInfoId);

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

        List<ProgramInfo> programInfoList = programInfoRepository.getAllByProgram_id(disciplineInfoProgram.getId());

        List<ProgramInfoDtoResponse> programInfoDtoResponses = new ArrayList<>();
        List<ProgramDetailDtoResponse> programDetailDtoResponses = new ArrayList<>();

        for (ProgramInfo item :
                programInfoList) {
                programInfoDtoResponses.add(ProgramInfoFacade.objectToDto(item));
                programDetailDtoResponses.add(ProgramDetailFacade.objectToDto(programDetailRepository.getByProgramInfoId(item.getId())));
        }

        response.setProgramInfo(programInfoDtoResponses);
        response.setProgramDetails(programDetailDtoResponses);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getUserData(Integer userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }
}
