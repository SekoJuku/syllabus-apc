package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class DisciplineInfoProgramDtoRequest {
    private Integer id;
    private Integer instructorId;
    private Integer disciplineInfoId;
    private Integer evaluationId;
}
