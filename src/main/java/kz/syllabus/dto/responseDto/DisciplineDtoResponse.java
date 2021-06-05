package kz.syllabus.dto.responseDto;


import lombok.Data;

@Data
public class DisciplineDtoResponse {
    private Integer id;
    private String name;
    private String language;
    private Integer credits;
    private Integer lectureHoursPerWeek;
    private Integer practiceHoursPerWeek;
    private Integer IswHoursPerWeek;
}
