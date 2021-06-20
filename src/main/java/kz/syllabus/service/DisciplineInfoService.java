package kz.syllabus.service;

import kz.syllabus.repository.SyllabusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DisciplineInfoService {
    private SyllabusRepository disciplineInfoRepository;


}
