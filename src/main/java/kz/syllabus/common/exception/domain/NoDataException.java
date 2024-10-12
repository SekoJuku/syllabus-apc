package kz.syllabus.common.exception.domain;

import kz.syllabus.common.exception.AbstractApiException;
import org.springframework.http.HttpStatus;

public class NoDataException extends AbstractApiException {

    public NoDataException(String message) {
        super(HttpStatus.NO_CONTENT, message);
    }

}
