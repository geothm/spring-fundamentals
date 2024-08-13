package ro.wantsome.databases.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<Book, Long> {

	List<Book> findAllByTitle(String title);

	List<Book> findAllByPriceGreaterThanAndPriceLessThan(Double price, Double price2);

	@Modifying
	@Query("UPDATE Book b SET b.author.name=:author, b.author.age=:age, b.price=:price ,b.title=:title WHERE b.id = :id")
	void updateBookById(String author, Double age, Double price, String title, Long id);
}
