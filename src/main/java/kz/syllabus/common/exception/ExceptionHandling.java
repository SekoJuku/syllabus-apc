package kz.syllabus.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException() {
        return createHttpExceptionResponse(
                HttpStatus.BAD_REQUEST,
                "Username and password credentials incorrect. Please try again");
    }

    @ExceptionHandler(AbstractApiException.class)
    public ResponseEntity<?> syllabusApcException(
            AbstractApiException exception) {
        return createHttpExceptionResponse(exception.getStatus(), exception.getMessage());
    }

    private ResponseEntity<HttpResponseException> createHttpExceptionResponse(
            HttpStatus httpStatus, String httpMessage) {
        return new ResponseEntity<>(
                new HttpResponseException(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase(), httpMessage),
                httpStatus
        );
    }

    record HttpResponseException(
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
            ZonedDateTime timeStamp,
            int httpStatusCode,
            HttpStatus httpStatus,
            String reason,
            String message
    ) {

        HttpResponseException(int httpStatusCode, HttpStatus httpStatus, String reason, String message) {
            this(ZonedDateTime.now(ZoneId.of("UTC")), httpStatusCode, httpStatus, reason, message);
        }
    }
}
