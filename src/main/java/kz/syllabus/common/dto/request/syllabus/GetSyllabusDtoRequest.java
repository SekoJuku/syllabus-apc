package kz.syllabus.common.dto.request.syllabus;

import lombok.Data;

@Data
public class GetSyllabusDtoRequest {
    private Long userId;
    private Long syllabusId;
}
