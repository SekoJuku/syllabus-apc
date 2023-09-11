package kz.syllabus.dto.response.syllabus;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MainPageDtoResponse {
    private Long                       id;
    private String                     name;
    private String                     disciplineName;
    private String                     year;
    private boolean                    isActive;
    private List<MainpageDtoComponent> instructors;
}
