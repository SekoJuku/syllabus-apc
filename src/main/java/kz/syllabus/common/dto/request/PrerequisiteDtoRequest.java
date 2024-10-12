package kz.syllabus.common.dto.request;

import lombok.Data;

@Data
public class PrerequisiteDtoRequest {
    private Long id;
    private Long disciplineId;
    private Long disciplineInfoId;
}
