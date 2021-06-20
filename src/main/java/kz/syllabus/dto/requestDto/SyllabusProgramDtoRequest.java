package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class SyllabusProgramDtoRequest {
    private Integer id;
    private Integer instructorId;
    private Integer disciplineInfoId;
    private Integer evaluationId;
    private String competencies;
}
