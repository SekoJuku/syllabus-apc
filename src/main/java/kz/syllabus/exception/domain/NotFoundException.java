package kz.syllabus.exception.domain;

import kz.syllabus.exception.SyllabusApcException;

import org.springframework.http.HttpStatus;

public class NotFoundException extends SyllabusApcException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
