package kz.syllabus.util;

import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.dto.response.syllabus.MainpageDtoComponent;
import kz.syllabus.persistence.model.PersonalInfo;
import kz.syllabus.persistence.model.syllabus.Syllabus;
import kz.syllabus.service.user.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SyllabusUtil {

    private final InstructorService instructorService;


    public List<MainPageDtoResponse> toMainPageDtoResponse(List<Syllabus> syllabusList) {
        return syllabusList.stream()
                .map(this::toResponse)
                .toList();
    }

    public MainPageDtoResponse toMainPageDtoResponse(Syllabus syllabus) {
        return toResponse(syllabus);
    }

    private MainPageDtoResponse toResponse(Syllabus item) {
        return MainPageDtoResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .year(item.getYear())
                .disciplineName(item.getDiscipline().getName())
                .instructors(
                        item.getInstructors().stream()
                                .map(instructorService::getPersonalInfo)
                                .map(this::toMainPageDtoComponent)
                                .toList()
                )
                .build();
    }

    private MainpageDtoComponent toMainPageDtoComponent(PersonalInfo info) {
        return MainpageDtoComponent.builder()
                .id(info.getUser().getId())
                .name(info.getName())
                .lastname(info.getSname())
                .build();
    }
}
