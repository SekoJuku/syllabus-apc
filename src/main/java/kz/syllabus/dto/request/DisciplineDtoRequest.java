package kz.syllabus.dto.request;


import lombok.Data;

@Data
public class DisciplineDtoRequest {
    private Long id;
    private String name;
    private String language;
    private Long credits;
    private Long lectureHoursPerWeek;
    private Long practiceHoursPerWeek;
    private Long IswHoursPerWeek;
}
