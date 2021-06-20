package kz.syllabus.dto.responseDto;

import kz.syllabus.dto.requestDto.PostrequisiteDtoRequest;
import kz.syllabus.dto.requestDto.PrerequisiteDtoRequest;
import kz.syllabus.dto.requestDto.ProgramDetailDtoRequest;
import kz.syllabus.dto.requestDto.ProgramInfoDtoRequest;
import lombok.Data;

import java.util.List;

@Data
public class FullSyllabusDtoResponse {
    private Integer id;
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
    private List<ProgramInfoDtoResponse> programInfo;
    private List<ProgramDetailDtoResponse> programDetails;
}
