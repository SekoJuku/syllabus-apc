package kz.syllabus.common.mapper;

import kz.syllabus.common.dto.request.syllabus.SyllabusProgramDtoRequest;
import kz.syllabus.common.dto.response.syllabus.SyllabusProgramDtoResponse;
import kz.syllabus.common.persistence.model.syllabus.SyllabusProgram;
import org.mapstruct.Mapper;

@Mapper
public interface SyllabusProgramMapper {

    SyllabusProgram map(SyllabusProgramDtoRequest request);

    SyllabusProgramDtoResponse map(SyllabusProgram syllabusProgram);

}
