package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class GetSyllabusDtoRequest {
   private Integer userId;
   private Integer syllabusId;
}
