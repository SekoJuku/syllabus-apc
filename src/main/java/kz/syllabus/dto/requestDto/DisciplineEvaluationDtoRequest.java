package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class DisciplineEvaluationDtoRequest {
    private Integer id;
    private Integer evaluationSystemId;
    private Integer disciplineId;
}
