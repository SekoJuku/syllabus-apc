package kz.syllabus.utils.facade;

import kz.syllabus.dto.responseDto.SyllabusDtoResponse;
import kz.syllabus.dto.responseDto.FullSyllabusDtoResponse;
import kz.syllabus.entity.Syllabus;

public class DisciplineInfoFacade {
    public static SyllabusDtoResponse objectToDto(Syllabus syllabus) {
        SyllabusDtoResponse response = new SyllabusDtoResponse();
        if(syllabus.getId() != null) {
            response.setId(syllabus.getId());
        }
//        response.setDisciplineId(syllabus.getDisciplineId());
        response.setCredits(syllabus.getCredits());
        response.setAim(syllabus.getAim());
        response.setTasks(syllabus.getTasks());
        response.setResults(syllabus.getResults());
        response.setMethodology(syllabus.getMethodology());
        return response;
    }

    public static FullSyllabusDtoResponse objectToSyllabusDto(Syllabus syllabus, FullSyllabusDtoResponse response) {
        if(syllabus.getId() != null) {
            response.setId(syllabus.getId());
        }
//        response.setDisciplineId(syllabus.getDisciplineId());
        response.setCredits(syllabus.getCredits());
        response.setAim(syllabus.getAim());
        response.setTasks(syllabus.getTasks());
        response.setResults(syllabus.getResults());
        response.setMethodology(syllabus.getMethodology());
        return response;
    }
}
