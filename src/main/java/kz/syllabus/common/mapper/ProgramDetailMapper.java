package kz.syllabus.common.mapper;

import kz.syllabus.common.dto.request.ProgramDetailDtoRequest;
import kz.syllabus.common.dto.response.ProgramDetailDtoResponse;
import kz.syllabus.common.persistence.model.ProgramDetail;
import org.mapstruct.Mapper;

@Mapper
public interface ProgramDetailMapper {
    ProgramDetail map(ProgramDetailDtoRequest request);

    ProgramDetailDtoResponse map(ProgramDetail programDetail);

}
