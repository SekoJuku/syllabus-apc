package kz.syllabus.dto.responseDto;


import kz.syllabus.entity.ProgramDetail;
import lombok.Data;

@Data
public class ProgramInfoDtoResponse {
    private Integer id;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Integer week;
    private ProgramDetail programDetail;
}
