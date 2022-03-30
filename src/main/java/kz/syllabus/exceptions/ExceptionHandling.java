package kz.syllabus.exceptions;


import lombok.extern.java.Log;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Log
@RestControllerAdvice
public class ExceptionHandling {


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponseException> badCredentialsException(){
        return createHttpExceptionResponse(HttpStatus.BAD_REQUEST, "Username and password credentials incorrect. Please try again");
    }

    public ResponseEntity<HttpResponseException> createHttpExceptionResponse(HttpStatus httpStatus, String httpMessage)
    {
        return new ResponseEntity<>(new HttpResponseException(
                httpStatus.value(),
                httpStatus,httpStatus.getReasonPhrase(),
                httpMessage
        ),httpStatus);
    }
}
