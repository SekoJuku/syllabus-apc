package kz.syllabus.dto.request.user;

import lombok.Data;

@Data
public class InstructorDtoRequest {
    private Long id;
    private Long userId;
    private Long DisciplineInfoId;
}
