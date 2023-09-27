package kz.syllabus.dto.response;


import kz.syllabus.persistence.model.ProgramDetail;
import lombok.Data;

@Data
public class ProgramInfoDtoResponse {
    private Long id;
    private String lectureTheme;
    private String practiceTheme;
    private String iswTheme;
    private Long week;
    private ProgramDetail programDetail;
}
