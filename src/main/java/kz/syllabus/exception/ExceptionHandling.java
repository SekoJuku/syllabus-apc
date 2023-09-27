package kz.syllabus.exception;

import kz.syllabus.exception.domain.NotFoundException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log
@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponseException> badCredentialsException() {
        return createHttpExceptionResponse(
                HttpStatus.BAD_REQUEST,
                "Username and password credentials incorrect. Please try again");
    }

    @ExceptionHandler(SyllabusApcException.class)
    public ResponseEntity<HttpResponseException> syllabusApcException(
            SyllabusApcException exception) {
        return createHttpExceptionResponse(exception.getStatus(), exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpResponseException> notFoundException(NotFoundException exception) {
        return createHttpExceptionResponse(exception.getStatus(), exception.getMessage());
    }

    public ResponseEntity<HttpResponseException> createHttpExceptionResponse(
            HttpStatus httpStatus, String httpMessage) {
        return new ResponseEntity<>(
                new HttpResponseException(
                        httpStatus.value(), httpStatus, httpStatus.getReasonPhrase(), httpMessage),
                httpStatus);
    }
}
