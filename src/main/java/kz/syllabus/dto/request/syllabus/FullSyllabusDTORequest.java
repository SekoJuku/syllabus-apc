package kz.syllabus.dto.request.syllabus;

import kz.syllabus.dto.request.ProgramDetailDtoRequest;

import lombok.Data;

import java.util.List;

@Data
public class FullSyllabusDTORequest {
    private Long id;
    private Long userId;
    private String iin;
    private String name;
    private String sname;
    private String mname;
    private String academicDegree;
    private String academicRank;
    private String email;
    private String phone;
    private String description;
    private Long disciplineId;
    private Long credits;
    private Long lectureHoursPerWeek;
    private Long practiceHoursPerWeek;
    private Long iswHoursPerWeek;
    private String aim;
    private String tasks;
    private String results;
    private String methodology;
    private String year;
    private String competencies;
    private Long evaluationId;
    private Long rubricId;
    private List<Long> prerequisites;
    private List<Long> postrequisites;
    private List<SyllabusProgramDtoRequest> syllabusProgram;
    private List<ProgramDetailDtoRequest> programDetails;
}
