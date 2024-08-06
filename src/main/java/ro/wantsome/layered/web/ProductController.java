package ro.wantsome.layered.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.wantsome.layered.domain.Product;
import ro.wantsome.layered.service.ProductService;

@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/productForm")
	public String productForm(Model model) {
		model.addAttribute("product", new Product());
		return "productForm";
	}

	@PostMapping("/submitProduct")
	public String saveProduct(@Valid @ModelAttribute Product product,
			BindingResult bindingResult,
			Model model) {

		if(bindingResult.hasErrors()) {
			return "productForm";
		}

		productService.addProduct(product);

		model.addAttribute("title", "Products");
		model.addAttribute("products", productService.listProducts());

		return "products";
	}

	@GetMapping("/products")
	public String getProducts(Model model) {
		model.addAttribute("title", "Products");
		model.addAttribute("products", productService.listProducts());
		return "products";
	}
}
