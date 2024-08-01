package ro.wantsome.databases.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<Book, Long> {

	List<Book> findAllByTitle(String title);

	List<Book> findAllByPriceGreaterThanAndPriceLessThan(Double price, Double price2);
}
