package ro.wantsome.layered.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.wantsome.layered.service.ProductService;

@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public String getProducts(Model model) {
		model.addAttribute("title", "Products");
		model.addAttribute("products", productService.listProducts());
		return "products";
	}
}
