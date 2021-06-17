package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class ListInstructorDisciplineDtoRequest {
    private Integer id;
    private Integer discipline_id;
    private Integer credits;
    private String aim;
    private String tasks;
    private String result;
    private String methodology;
}
