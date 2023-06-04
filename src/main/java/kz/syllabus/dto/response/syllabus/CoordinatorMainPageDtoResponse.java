package kz.syllabus.dto.response.syllabus;

import lombok.Data;

import java.util.List;

@Data
public class CoordinatorMainPageDtoResponse {
    private Long id;
    private String name;
    private String year;
    private String disciplineName;
    private List<MainpageDtoComponent> instructors;
}