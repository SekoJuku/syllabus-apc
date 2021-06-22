package kz.syllabus.dto.responseDto;


import lombok.Data;

@Data
public class SyllabusProgramDtoResponse {
    private Integer id;
    private Integer syllabusId;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Integer week;
}
