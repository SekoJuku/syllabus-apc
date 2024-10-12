package kz.syllabus.common.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDtoRequest {
    private String username;
    private CharSequence password;
}
