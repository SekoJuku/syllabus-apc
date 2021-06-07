package kz.syllabus.controllers.teachers;


import kz.syllabus.exceptions.ExceptionHandling;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/teacher")
public class TeacherController extends ExceptionHandling {

    @PostMapping("")
    public ResponseEntity<?> profile(){
        return ResponseEntity.ok("Success");
    }

}
