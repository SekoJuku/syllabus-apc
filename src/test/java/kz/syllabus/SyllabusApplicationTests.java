package kz.syllabus;

import kz.syllabus.controllers.coordinator.CoordinatorController;
import kz.syllabus.controllers.dean.DeanController;
import kz.syllabus.controllers.students.StudentController;
import kz.syllabus.controllers.teachers.InstructorController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SyllabusApplication.class)
class SyllabusApplicationTests {

    @Autowired
    private CoordinatorController coordinatorController;

    @Autowired
    private DeanController deanController;

    @Autowired
    private StudentController studentController;

    @Autowired
    private InstructorController instructorController;


    @Test
    void contextLoads() {
    }

}
