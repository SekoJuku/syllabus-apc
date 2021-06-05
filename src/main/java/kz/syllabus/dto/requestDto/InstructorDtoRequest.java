package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class InstructorDtoRequest {
    private Integer id;
    private Integer userId;
    private Integer DisciplineInfoId;
}
