package kz.syllabus.service;


import kz.syllabus.entity.Instructor;
import kz.syllabus.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public ResponseEntity<?> getSyllabusesByInstructor(Integer id) {
        System.out.println(instructorRepository.getSyllabusesByInstructor(id));
        return ResponseEntity.ok(instructorRepository.getSyllabusesByInstructor(id));
    }
}
