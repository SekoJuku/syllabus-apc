package kz.syllabus.dto.requestDto;

import kz.syllabus.entity.SyllabusProgram;
import lombok.Data;

import java.util.List;

@Data
public class FullSyllabusDTORequest {
    private Integer id;
    private Integer userId;
    private String iin;
    private String name;
    private String sname;
    private String mname;
    private String academicDegree;
    private String academicRank;
    private String email;
    private String phone;
    private String description;
    private Integer disciplineId;
    private Integer credits;
    private Integer lectureHoursPerWeek;
    private Integer practiceHoursPerWeek;
    private Integer iswHoursPerWeek;
    private String aim;
    private String tasks;
    private String results;
    private String methodology;
    private String year;
    private String competencies;
    private Integer evaluationId;
    private Integer rubricId;
    private List<Integer> prerequisites;
    private List<Integer> postrequisites;
    private List<SyllabusProgramDtoRequest> syllabusProgram;
    private List<ProgramDetailDtoRequest> programDetails;
}
