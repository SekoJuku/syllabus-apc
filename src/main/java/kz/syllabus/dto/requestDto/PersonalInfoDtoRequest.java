package kz.syllabus.dto.requestDto;


import lombok.Data;

@Data
public class PersonalInfoDtoRequest {
    private Integer id;
    private Integer userId;
    private String sname;
    private String name;
    private String mname;
    private String academicDegree;
    private String academicRank;
    private Integer positionId;
    private String email;
    private String phone;
    private String description;
}
