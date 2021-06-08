package kz.syllabus.dto.requestDto;

import lombok.Data;

import java.util.List;

@Data
public class SyllabusDTORequest {
    private Integer userId;
    private Integer disciplineId;
    private Integer credits;
    private Integer lectureHoursPerWeek;
    private Integer practiceHoursPerWeek;
    private Integer iswHoursPerWeek;
    private String aim;
    private String tasks;
    private String results;
    private String methodology;
    private String competencies;
    private Integer evaluationId;
    private List<Integer> prerequisites;
    private List<Integer> postrequisites;
    private List<ProgramInfoDtoRequest> programInfo;
    private List<ProgramDetailDtoRequest> programDetails;
}
