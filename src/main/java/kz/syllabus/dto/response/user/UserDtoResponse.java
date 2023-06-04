package kz.syllabus.dto.response.user;

import lombok.Data;

@Data
public class UserDtoResponse {
    private Long id;
    private String username;
    private String password;
    private RoleDtoResponse role;
}
