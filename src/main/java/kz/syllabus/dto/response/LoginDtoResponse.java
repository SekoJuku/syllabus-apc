package kz.syllabus.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class LoginDtoResponse {
    private String accessToken;
    private String refreshToken;
}
