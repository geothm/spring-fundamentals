package ro.wantsome.layered.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

	public List<Product> findAll() {
		return List.of(
			new Product(1L, "Product 1",  100.0, true),
			new Product(2L, "Product 2",  200.0, true),
			new Product(3L, "Product 3",  300.0, false));
	}
}
