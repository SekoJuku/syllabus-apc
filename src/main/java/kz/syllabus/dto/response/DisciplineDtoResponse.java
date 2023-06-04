package kz.syllabus.dto.response;

import lombok.Data;

@Data
public class DisciplineDtoResponse {
    private Long id;
    private String name;
    private String language;
    private Long credits;
    private Long lectureHoursPerWeek;
    private Long practiceHoursPerWeek;
    private Long IswHoursPerWeek;
}
