package kz.syllabus.common.exception.domain;

import kz.syllabus.common.exception.AbstractApiException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends AbstractApiException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
