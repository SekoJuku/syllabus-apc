package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class ProgramInfoDtoRequest {
    private Integer id;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Integer week;
}
