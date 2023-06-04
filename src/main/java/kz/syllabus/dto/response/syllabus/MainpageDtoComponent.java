package kz.syllabus.dto.response.syllabus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MainpageDtoComponent {
    private Long id;
    private String name;
    private String lastname;
}
