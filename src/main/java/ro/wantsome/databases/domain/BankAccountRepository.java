package ro.wantsome.databases.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankAccountRepository
{

	@PersistenceContext
	private EntityManager em;

	public void save(BankAccount bankAccount) {
		if (bankAccount.getId() == null) {
			em.persist(bankAccount);
		}
		else {
			em.merge(bankAccount);
		}
	}

	public BankAccount findById(Long id) {
		return em.find(BankAccount.class, id);
	}

	public List<BankAccount> findAll() {
		return em.createQuery("SELECT bankAccount FROM BankAccount bankAccount", BankAccount.class).getResultList();
	}

	public void delete(Long id) {
		BankAccount bankAccount = findById(id);
		em.remove(bankAccount);
	}

}
