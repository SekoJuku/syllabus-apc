package kz.syllabus.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class CoordinatorMainPageDtoResponse {
    private Integer id;
    private String name;
    private String year;
    private String disciplineName;
    private List<MainpageDtoComponent> instructors;
}
