package kz.syllabus.common.dto.response.syllabus;

import kz.syllabus.common.dto.response.user.InstructorDtoResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MainPageDtoResponse {
    private Long id;
    private String name;
    private String disciplineName;
    private String year;
    private boolean isActive;
    private List<InstructorDtoResponse> instructors;
}
