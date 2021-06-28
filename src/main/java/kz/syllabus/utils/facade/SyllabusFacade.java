package kz.syllabus.utils.facade;

import kz.syllabus.dto.responseDto.SyllabusDtoResponse;
import kz.syllabus.dto.responseDto.FullSyllabusDtoResponse;
import kz.syllabus.entity.ProgramDetail;
import kz.syllabus.entity.Syllabus;
import kz.syllabus.entity.SyllabusProgram;
import lombok.extern.java.Log;

import java.util.HashSet;
import java.util.Set;

@Log
public class SyllabusFacade {
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

    public static boolean checkSimilarity(Syllabus syllabus1, Syllabus syllabus2) {
        boolean u = false;
        if(syllabus1.getName().equals(syllabus2.getName())) {
            if (syllabus1.getAim().equals(syllabus2.getAim())) {
                if (syllabus1.getTasks().equals(syllabus2.getTasks())) {
                    if (syllabus1.getResults().equals(syllabus2.getResults())) {
                        if (syllabus1.getMethodology().equals(syllabus2.getMethodology())) {
                            if (syllabus1.getYear().equals(syllabus2.getYear())) {
                                if (syllabus1.getEvaluationId().equals(syllabus2.getEvaluationId())) {
                                    if (syllabus1.getCompetences().equals(syllabus2.getCompetences())) {
                                        if (syllabus1.getRubricId().equals(syllabus2.getRubricId())) {
                                            for (SyllabusProgram i :
                                                    syllabus1.getSyllabusProgram()) {
                                                if(i.getWeek() == 9 || i.getWeek() == 10) {
                                                    continue;
                                                }
                                                if(u) {
                                                    break;
                                                }
                                                for (SyllabusProgram j :
                                                        syllabus2.getSyllabusProgram()) {
                                                    if(i.getWeek().equals(j.getWeek())) {
                                                        if (!i.getLectureTheme().equals(j.getLectureTheme())) {
                                                            u = true;
                                                            log.info("lectheme");
                                                            break;

                                                        }
                                                        if (!i.getPracticeTheme().equals(j.getPracticeTheme())) {
                                                            u = true;
                                                            log.info("pratheme");
                                                            break;
                                                        }
                                                        if (!i.getIswTheme().equals(j.getIswTheme())) {
                                                            u = true;
                                                            log.info("iswtheme");
                                                            break;
                                                        }
                                                        ProgramDetail programDetail1 = i.getProgramDetail();
                                                        ProgramDetail programDetail2 = j.getProgramDetail();
                                                        if (!programDetail1.getLectureFof().equals(programDetail2.getLectureFof())) {
                                                            u = true;
                                                            log.info("lecfof");
                                                            break;
                                                        }
                                                        if (!programDetail1.getPracticeFof().equals(programDetail2.getPracticeFof())) {
                                                            u = true;
                                                            log.info("prafof");
                                                            break;

                                                        }
                                                        if (!programDetail1.getIswFof().equals(programDetail2.getIswFof())) {
                                                            u = true;
                                                            log.info("iswfof");
                                                            break;

                                                        }
                                                        if(!programDetail1.getLectureLiterature().equals(programDetail2.getLectureLiterature())) {
                                                            u = true;
                                                            log.info("leclit");
                                                            break;

                                                        }
                                                        if(!programDetail1.getPracticeLiterature().equals(programDetail2.getPracticeLiterature())) {
                                                            u = true;
                                                            log.info("pralit");
                                                            break;

                                                        }
                                                        if(!programDetail1.getIswLiterature().equals(programDetail2.getIswLiterature())) {
                                                            u = true;
                                                            log.info("iswlit");
                                                            break;

                                                        }
                                                        if(!programDetail1.getLectureAssessment().equals(programDetail2.getLectureAssessment())) {
                                                            u = true;
                                                            log.info("lecass");
                                                            break;

                                                        }
                                                        if(!programDetail1.getPracticeAssessment().equals(programDetail2.getPracticeAssessment())) {
                                                            u = true;
                                                            log.info("praass");
                                                            break;

                                                        }
                                                        if(!programDetail1.getIswAssessment().equals(programDetail2.getIswAssessment())) {
                                                            u = true;
                                                            log.info("iwsass");
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            log.info("rubricId");
                                            return false;
                                        }
                                    } else {
                                        log.info("competency");
                                        return false;
                                    }
                                } else {
                                    log.info("eva");
                                    return false;
                                }
                            } else {
                                log.info("year");
                                return false;
                            }
                        } else {
                            log.info("meth");
                            return false;
                        }
                    } else {
                        log.info("res");
                        return false;
                    }
                } else {
                    log.info("task");
                    return false;
                }
            } else {
                log.info("aim");
                return false;
            }
        } else {
            log.info(syllabus1.getName() + "1  2" + syllabus2.getName());
            return false;
        }
//        syllabus1.setId(null);
//        syllabus2.setId(null);
//        int i = 1;
//        for (SyllabusProgram item :
//                syllabus1.getSyllabusProgram()) {
//            log.info(String.valueOf(i));
//            if(i == 10) {
//                item.setProgramDetail(null);
//                break;
//            }
//            item.setId(null);
//            item.setSyllabusId(null);
//            ProgramDetail programDetail = item.getProgramDetail();
//            programDetail.setId(null);
//            programDetail.setSyllabusProgramId(null);
//            i++;
//        }
//        i = 1;
//        for (SyllabusProgram item :
//                syllabus2.getSyllabusProgram()) {
//            log.info(String.valueOf(i));
//            if(i == 10) {
//                item.setProgramDetail(null);
//                break;
//            }
//            item.setId(null);
//            item.setSyllabusId(null);
//            ProgramDetail programDetail = item.getProgramDetail();
//            programDetail.setId(null);
//            programDetail.setSyllabusProgramId(null);
//            i++;
//        }
//        syllabus1.setSyllabusParam(null);
//        syllabus2.setSyllabusParam(null);
//        return syllabus1.equals(syllabus2);
    if(!u)
        return true;
    return false;
    }
}
