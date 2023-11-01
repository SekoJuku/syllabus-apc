package kz.syllabus.converter;

import kz.syllabus.dto.response.syllabus.MainPageDtoResponse;
import kz.syllabus.dto.response.user.InstructorDtoResponse;
import kz.syllabus.persistence.model.PersonalInfo;
import kz.syllabus.persistence.model.syllabus.Syllabus;
import kz.syllabus.service.user.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SyllabusMainPageDtoConverter implements Converter<Syllabus, MainPageDtoResponse> {

    private final InstructorService instructorService;

    @Override
    public MainPageDtoResponse convert(Syllabus item) {
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

    private InstructorDtoResponse toMainPageDtoComponent(PersonalInfo info) {
        return InstructorDtoResponse.builder()
                                    .id(info.getUser().getId())
                                    .name(info.getName())
                                    .lastname(info.getSname())
                                    .build();
    }

}
