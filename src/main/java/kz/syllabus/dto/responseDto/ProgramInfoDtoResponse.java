package kz.syllabus.dto.responseDto;


import lombok.Data;

@Data
public class ProgramInfoDtoResponse {
    private Integer id;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Integer week;
    private String competencies;
}
