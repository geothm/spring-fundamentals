package ro.wantsome.databases.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Book book) {
		if (book.getId() == null) {
			em.persist(book);
		}
		else {
			em.merge(book);
		}
	}

	public Book findById(Long id) {
		return em.find(Book.class, id);
	}

	public List<Book> findAll() {
		return em.createQuery("SELECT book FROM Book book", Book.class).getResultList();
	}
}
