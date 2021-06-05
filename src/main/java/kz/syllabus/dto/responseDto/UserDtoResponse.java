package kz.syllabus.dto.responseDto;

import lombok.Data;

@Data
public class UserDtoResponse {
    private Integer id;
    private String username;
    private String password;
    private Integer roleId;
}
