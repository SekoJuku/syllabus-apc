package kz.syllabus.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginDtoResponse {
    private String accessToken;
    private String refreshToken;
}
