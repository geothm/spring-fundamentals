package ro.wantsome.databases.service.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ro.wantsome.databases.domain.async.Enrollment;
import ro.wantsome.databases.domain.async.Student;
import ro.wantsome.databases.domain.async.StudentAverage;
import ro.wantsome.databases.domain.async.repository.EnrollmentRepository;
import ro.wantsome.databases.domain.async.repository.StudentAverageRepository;
import ro.wantsome.databases.domain.async.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentAverageRepository studentAverageRepository;

    public StudentService(StudentRepository studentRepository, EnrollmentRepository enrollmentRepository, StudentAverageRepository studentAverageRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentAverageRepository = studentAverageRepository;
    }

    @Async
    public void calculateAndSaveYearlyAverages() {
        List<Student> students = studentRepository.findAll();

        for (Student student: students) {
            List<Enrollment> enrollments = enrollmentRepository.findByStudentId(student.getId());
            double average = enrollments
                    .stream()
                    .mapToDouble(Enrollment::getGrade)
                    .average()
                    .orElse(0.0);

            StudentAverage studentAverage = studentAverageRepository.findByStudentId(student.getId())
                    .orElse(new StudentAverage());

            studentAverage.setStudent(student);
            studentAverage.setAverageGrade(average);

            studentAverageRepository.save(studentAverage);
        }
    }

    public List<StudentAverage> getAllAverages() {
        return studentAverageRepository.findAll();
    }
}
