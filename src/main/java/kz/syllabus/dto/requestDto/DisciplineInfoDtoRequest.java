package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class DisciplineInfoDtoRequest {
    private Integer id;
    private Integer disciplineId;
    private Integer credits;
    private String aim;
    private String tasks;
    private String results;
    private String methodology;
}
