package kz.syllabus.common.dto.request.syllabus;


import lombok.Data;

@Data
public class SyllabusProgramDtoRequest {
    private Long id;
    private Long syllabusId;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Long week;
}
