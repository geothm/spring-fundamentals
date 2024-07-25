package ro.wantsome.layered.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

	private List<Product> products;
	private Long idCounter = 1L;

	public ProductRepository() {
		products = new ArrayList<>();
	}

	public List<Product> findAll() {
		return products;
	}

	public void save(Product product) {
		product.setId(idCounter++);
		products.add(product);
	}
}
