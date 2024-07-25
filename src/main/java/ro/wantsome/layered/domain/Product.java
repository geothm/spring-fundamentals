package ro.wantsome.layered.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public class Product {

	private Long id;

	@NotBlank(message = "Name is mandatory")
	@Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
	private String name;

	@NotNull(message = "Price is mandatory")
	private Double price;

	private boolean available;

	public Product(Long id, String name, Double price, boolean available) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.available = available;
	}

	public Product() {
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public Double getPrice() {
		return price;
	}

	public boolean isAvailable() {
		return available;
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", name='" + name + '\'' + ", description='"  + '\'' + ", price=" + price + '}';
	}
}
