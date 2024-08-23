package ro.wantsome.databases.domain.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.wantsome.databases.domain.batch.Visitors;

public interface VisitorsRepository extends JpaRepository<Visitors, Long> {
}
