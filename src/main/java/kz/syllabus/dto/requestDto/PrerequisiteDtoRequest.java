package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class PrerequisiteDtoRequest {
    private Integer id;
    private Integer disciplineId;
    private Integer disciplineInfoId;
}
