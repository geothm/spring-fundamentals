package ro.wantsome.databases.domain.async.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.wantsome.databases.domain.async.StudentAverage;

import java.util.Optional;

public interface StudentAverageRepository extends JpaRepository<StudentAverage, Long> {
    Optional<StudentAverage> findByStudentId(Long studentId);
}
