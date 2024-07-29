package ro.wantsome.databases.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Customer customer) {
		if (customer.getId() == null) {
			em.persist(customer);
		}
		else {
			em.merge(customer);
		}
	}

	public Customer findById(Long id) {
		return em.find(Customer.class, id);
	}

	public List<Customer> findAll() {
		return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
	}
}
