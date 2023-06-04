package kz.syllabus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class SyllabusApcException extends Exception {
    private HttpStatus status;
    private String message;
}
