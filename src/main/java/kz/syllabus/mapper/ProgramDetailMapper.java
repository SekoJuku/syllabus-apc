package kz.syllabus.mapper;

import kz.syllabus.dto.request.ProgramDetailDtoRequest;
import kz.syllabus.dto.response.ProgramDetailDtoResponse;
import kz.syllabus.persistence.model.ProgramDetail;
import org.mapstruct.Mapper;

@Mapper
public interface ProgramDetailMapper {
    ProgramDetail map(ProgramDetailDtoRequest request);

    ProgramDetailDtoResponse map(ProgramDetail programDetail);

}
