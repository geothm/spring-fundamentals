package ro.wantsome.databases.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Customer customer) {
		em.persist(customer);
	}
}
