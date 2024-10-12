package kz.syllabus.common.dto.request.user;

import lombok.Data;

@Data
public class UserDtoRequest {
    private Long id;
    private String username;
    private String password;
    private Long roleId;
}
