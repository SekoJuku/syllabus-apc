package kz.syllabus.dto.requestDto;

import lombok.Data;

@Data
public class PostrequisiteDtoRequest {
    private Integer id;
    private Integer disciplineId;
    private Integer disciplineInfoId;
}
