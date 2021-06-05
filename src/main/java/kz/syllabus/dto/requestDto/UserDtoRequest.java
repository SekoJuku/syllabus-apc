package kz.syllabus.dto.requestDto;

import lombok.Data;

@Data
public class UserDtoRequest {
    private Integer id;
    private String username;
    private String password;
    private Integer roleId;
}
