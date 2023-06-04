package kz.syllabus.dto.response.user;


import lombok.Data;

@Data
public class PersonalInfoDtoResponse {
    private Long id;
    private Long userId;
    private String sname;
    private String name;
    private String mname;
    private String academicDegree;
    private String academicRank;
    private Long positionId;
    private String email;
    private String phone;
    private String description;
}
