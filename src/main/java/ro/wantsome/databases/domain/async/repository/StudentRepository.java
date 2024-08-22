package ro.wantsome.databases.domain.async.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.wantsome.databases.domain.async.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
