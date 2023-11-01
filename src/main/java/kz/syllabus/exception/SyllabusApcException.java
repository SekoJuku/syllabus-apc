package kz.syllabus.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class SyllabusApcException extends Exception {
    private final HttpStatus status;
    private final String message;
}
