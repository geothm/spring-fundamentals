package ro.wantsome.databases.web.async;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.wantsome.databases.domain.async.StudentAverage;
import ro.wantsome.databases.service.async.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/calculate-averages")
    public ResponseEntity<String> calculateAverages() {
        System.out.println("Starting to calculate averages for students");
        studentService.calculateAndSaveYearlyAverages();
        return ResponseEntity.ok("Finished to calculate averages for students");
    }

    @GetMapping("/averages")
    public List<StudentAverage> getAverages() {
        System.out.println("Retrieve all students averages");
        return studentService.getAllAverages();
    }
}
