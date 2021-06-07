package kz.syllabus.service;

import kz.syllabus.repository.DisciplineInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DisciplineInfoService {
    private DisciplineInfoRepository disciplineInfoRepository;


}
