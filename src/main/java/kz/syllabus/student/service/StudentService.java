package kz.syllabus.student.service;

import kz.syllabus.common.persistence.model.syllabus.Syllabus;

import java.util.List;

public interface StudentService {
    List<Syllabus> findAll();
}
