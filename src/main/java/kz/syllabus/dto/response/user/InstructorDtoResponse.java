package kz.syllabus.dto.response.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstructorDtoResponse {
    private Long id;
    private String name;
    private String lastname;
}
