package kz.syllabus.common.dto.request.syllabus;


import lombok.Data;

@Data
public class SyllabusDtoRequest {
    private Long id;
    private Long disciplineId;
    private Long credits;
    private String aim;
    private String tasks;
    private String results;
    private String methodology;
}
