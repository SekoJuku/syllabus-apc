package kz.syllabus.common.exception.domain;

import kz.syllabus.common.exception.AbstractApiException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractApiException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
