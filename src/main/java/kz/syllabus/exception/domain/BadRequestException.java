package kz.syllabus.exception.domain;

import kz.syllabus.exception.SyllabusApcException;

import org.springframework.http.HttpStatus;

public class BadRequestException extends SyllabusApcException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
