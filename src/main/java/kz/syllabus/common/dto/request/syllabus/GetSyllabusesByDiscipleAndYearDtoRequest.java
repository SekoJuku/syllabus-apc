package kz.syllabus.common.dto.request.syllabus;

import lombok.Data;

@Data
public class GetSyllabusesByDiscipleAndYearDtoRequest {
    private Long disciplineId;
    private String year;
    private Long userId;
}
