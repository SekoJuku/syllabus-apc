package kz.syllabus.dto.requestDto;

import lombok.Data;

@Data
public class GetSyllabusesByDiscipleAndYearDtoRequest {
    private Integer disciplineId;
    private String year;
    private Integer userId;
}
