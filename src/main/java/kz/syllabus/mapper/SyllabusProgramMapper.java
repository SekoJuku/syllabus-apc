package kz.syllabus.mapper;

import kz.syllabus.dto.request.syllabus.SyllabusProgramDtoRequest;
import kz.syllabus.dto.response.syllabus.SyllabusProgramDtoResponse;
import kz.syllabus.persistence.model.syllabus.SyllabusProgram;
import org.mapstruct.Mapper;

@Mapper
public interface SyllabusProgramMapper {

    SyllabusProgram map(SyllabusProgramDtoRequest request);

    SyllabusProgramDtoResponse map(SyllabusProgram syllabusProgram);

}
