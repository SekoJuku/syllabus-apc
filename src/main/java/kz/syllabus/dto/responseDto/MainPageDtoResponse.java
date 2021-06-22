package kz.syllabus.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class MainPageDtoResponse {
    private Integer id;
    private String name;
    private String discipline;
    private String year;
    private boolean isActive;
    private List<MainpageDtoComponent> instructors;
}
