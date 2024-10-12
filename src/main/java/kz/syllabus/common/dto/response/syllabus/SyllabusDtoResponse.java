package kz.syllabus.common.dto.response.syllabus;

import kz.syllabus.common.dto.response.ProgramDetailDtoResponse;
import kz.syllabus.common.dto.response.user.InstructorDtoResponse;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SyllabusDtoResponse extends ShortSyllabusDtoResponse {
    private List<InstructorDtoResponse> instructors;
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
