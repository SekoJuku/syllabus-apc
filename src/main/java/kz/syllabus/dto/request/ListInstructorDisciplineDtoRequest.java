package kz.syllabus.dto.request;


import lombok.Data;

@Data
public class ListInstructorDisciplineDtoRequest {
    private Long id;
    private Long discipline_id;
    private Long credits;
    private String aim;
    private String tasks;
    private String result;
    private String methodology;
}
