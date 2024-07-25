package ro.wantsome.layered.domain;

public class Product {

	private Long id;
	private String name;
	private double price;
	private boolean available;

	public Product(Long id, String name, double price, boolean available) {
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

	public void setPrice(double price) {
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


	public double getPrice() {
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
