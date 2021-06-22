package kz.syllabus.controllers.teachers;


import kz.syllabus.dto.requestDto.GetSyllabusesByDiscipleAndYearDtoRequest;
import kz.syllabus.dto.requestDto.GetUserDataDtoRequest;
import kz.syllabus.dto.requestDto.FullSyllabusDTORequest;
import kz.syllabus.dto.requestDto.GetSyllabusDtoRequest;
import kz.syllabus.entity.*;
import kz.syllabus.exceptions.ExceptionHandling;
import kz.syllabus.repository.PersonalInfoRepository;
import kz.syllabus.repository.PrerequisiteRepository;
import kz.syllabus.service.PersonalInfoService;
import kz.syllabus.service.SyllabusService;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController extends ExceptionHandling {
    private final SyllabusService syllabusService;
    private final PersonalInfoService personalInfoService;


//    @PostMapping("")
//    public ResponseEntity<?> getAll(@RequestBody GetSyllabusDtoRequest request) {
//        return syllabusService.getAll(request.getUserId());
//    }

    @PostMapping("/syllabus/create")
    public ResponseEntity<?> createSyllabus(@RequestBody FullSyllabusDTORequest fullSyllabusDTORequest) {
        return syllabusService.create(fullSyllabusDTORequest);
    }
    @PostMapping("/syllabus")
    public ResponseEntity<?> getSyllabus(@RequestBody GetSyllabusDtoRequest getSyllabusDtoRequest) {
        return syllabusService.getSyllabus(getSyllabusDtoRequest.getUserId(), getSyllabusDtoRequest.getSyllabusId());
    }

    @GetMapping("/syllabus/{id}")
    public Syllabus getSyllabusById(@PathVariable Integer id){
        return syllabusService.getSyllabusById(id);
    }

    @GetMapping("/syllabus/delete/{id}")
    public ResponseEntity<?> deleteSyllabusById(@PathVariable Integer id) {
        return syllabusService.deleteSyllabusById(id);
    }
    @GetMapping("/checkFinal/{id}")
    public ResponseEntity<?> checkFinal(@PathVariable Integer id) {
        return syllabusService.checkForFinal(id);
    }

    @PostMapping("/data")
    public ResponseEntity<?> getData(@RequestBody GetUserDataDtoRequest getUserDataDtoRequest) {
        return syllabusService.getUserData(getUserDataDtoRequest.getUserId());
    }
    @PostMapping("/disciplines")
    public ResponseEntity<?> getDisciplines(@RequestBody GetUserDataDtoRequest request) {
        return syllabusService.getDisciplines(request.getUserId());
    }
//    @PostMapping("/")
//    public ResponseEntity<?> getSyllabusesByDiscipleAndYear(@RequestBody GetSyllabusesByDiscipleAndYearDtoRequest request) {
//        return syllabusService.getSyllabusesByDiscipleAndYear(request.getUserId(),request.getDisciplineId(), request.getYear());
//    }

    @GetMapping("/pdf/export/syllabus/{id}")
    public void exportToPDF(HttpServletResponse response, @PathVariable Integer id) throws IOException {
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=syllabus.pdf";

        response.setHeader(headerKey, headerValue);
        Syllabus syllabus = syllabusService.getSyllabusById(id);
        Integer userId = syllabus.getInstructor().getUser().getPersonalInfo().getId();
        PersonalInfo personalInfo = personalInfoService.getPersonalInfoByUserId(userId);
        Prerequisite prerequisite = syllabusService.findPrerequisiteById(id);
        Postrequisite postrequisite = syllabusService.findPostrequisiteById(id);
        Integer syllabusIdPrereq = prerequisite.getSyllabusId();
        Integer syllabusIdPostreq = postrequisite.getSyllabusId();
        Discipline discipline1 = syllabusService.getDisciplineById(syllabusIdPrereq);
        Discipline discipline2 = syllabusService.getDisciplineById(syllabusIdPostreq);
        SyllabusProgram syllabusProgram = syllabusService.getSyllabusProgramById(id);
        Integer syllabusProgramId = syllabusProgram.getId();
        ProgramDetail programDetail = syllabusService.getProgramDetailById(syllabusProgramId);
        TeacherPDFExporter exporter = new TeacherPDFExporter(syllabus, personalInfo, prerequisite, postrequisite, discipline1, discipline2, syllabusProgram, programDetail);
        exporter.export(response, id);
    }

}
