package kz.syllabus.dto.request;


import lombok.Data;

@Data
public class ProgramInfoDtoRequest {
    private Long id;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Long week;
}
