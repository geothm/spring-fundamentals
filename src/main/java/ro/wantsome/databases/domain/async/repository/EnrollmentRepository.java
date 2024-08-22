package ro.wantsome.databases.domain.async.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.wantsome.databases.domain.async.Enrollment;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
}
