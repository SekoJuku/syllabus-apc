package kz.syllabus.dto.response.syllabus;

import lombok.Data;

@Data
public class SyllabusDtoResponse {
    private Long id;
    private String name;
    private Long disciplineId;
    private Long credits;
    private String aim;
    private String tasks;
    private String results;
    private String methodology;
}
