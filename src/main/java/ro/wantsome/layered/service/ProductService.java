package ro.wantsome.layered.service;

import org.springframework.stereotype.Service;
import ro.wantsome.layered.domain.Product;
import ro.wantsome.layered.domain.ProductRepository;

import java.util.List;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> listProducts() {
		return productRepository.findAll();
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

}
