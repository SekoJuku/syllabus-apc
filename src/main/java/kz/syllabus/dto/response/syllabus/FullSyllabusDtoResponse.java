package kz.syllabus.dto.response.syllabus;

import kz.syllabus.dto.response.ProgramDetailDtoResponse;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class FullSyllabusDtoResponse extends SyllabusDtoResponse {
    private List<MainpageDtoComponent> instructors;
    private Long lectureHoursPerWeek;
    private Long practiceHoursPerWeek;
    private Long iswHoursPerWeek;
    private String year;
    private String competencies;
    private Long evaluationId;
    private Long rubricId;
    private List<Long> prerequisites;
    private List<Long> postrequisites;
    private List<SyllabusProgramDtoResponse> syllabusProgram;
    private List<ProgramDetailDtoResponse> programDetails;
}
