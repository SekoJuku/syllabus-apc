package kz.syllabus.dean.service;

import kz.syllabus.common.persistence.model.syllabus.Syllabus;
import kz.syllabus.common.persistence.model.syllabus.SyllabusParam;

import java.util.List;

public interface DeanService {

    List<Syllabus> getAll();

    SyllabusParam approve(Long id);

}
