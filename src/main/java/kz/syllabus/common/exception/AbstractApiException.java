package kz.syllabus.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public abstract class AbstractApiException extends Exception {
    private final HttpStatus status;
    private final String message;
}
