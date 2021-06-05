package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class DisciplineDtoRequest {
    private Integer id;
    private String name;
    private String language;
    private Integer credits;
    private Integer lectureHoursPerWeek;
    private Integer practiceHoursPerWeek;
    private Integer IswHoursPerWeek;
}
