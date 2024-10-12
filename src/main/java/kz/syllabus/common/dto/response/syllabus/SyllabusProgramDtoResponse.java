package kz.syllabus.common.dto.response.syllabus;


import lombok.Data;

@Data
public class SyllabusProgramDtoResponse {
    private Long id;
    private Long syllabusId;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Long week;
}
