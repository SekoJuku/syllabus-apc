package kz.syllabus.controllers.teachers;


import kz.syllabus.service.InstructorService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    /*@GetMapping("/api/instructor/{id}")
    public ResponseEntity<?> getSyllabusesByInstructor(@PathVariable Integer id){
        return ResponseEntity.ok(instructorService.getSyllabusesByInstructor(id));
    }*/

}
