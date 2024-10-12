package kz.syllabus.common.service;

import kz.syllabus.common.dto.request.TokenDtoRequest;
import kz.syllabus.common.dto.request.user.LoginDtoRequest;
import kz.syllabus.common.dto.response.TokenDtoResponse;

public interface AuthService {
    TokenDtoResponse auth(LoginDtoRequest request);

    TokenDtoResponse refresh(TokenDtoRequest request);
}
