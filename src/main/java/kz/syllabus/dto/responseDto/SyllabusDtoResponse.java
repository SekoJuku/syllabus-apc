package kz.syllabus.dto.responseDto;

import lombok.Data;

@Data
public class SyllabusDtoResponse {
    private Integer id;
    private Integer disciplineId;
    private Integer credits;
    private String aim;
    private String tasks;
    private String results;
    private String methodology;
}
