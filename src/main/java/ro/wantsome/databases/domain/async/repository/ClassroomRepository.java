package ro.wantsome.databases.domain.async.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.wantsome.databases.domain.async.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
