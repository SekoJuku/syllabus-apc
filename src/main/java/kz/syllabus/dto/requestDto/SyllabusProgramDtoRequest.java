package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class SyllabusProgramDtoRequest {
    private Integer id;
    private Integer syllabusId;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Integer week;
}
