package ro.wantsome.databases.web;

import org.springframework.stereotype.Controller;
import ro.wantsome.databases.domain.Customer;
import ro.wantsome.databases.service.CustomerService;

import javax.annotation.PostConstruct;

@Controller
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostConstruct
	void init(){
		Customer customer = new Customer();
		customer.setName("John Doe");

		System.out.println("Saving customer: " + customer);

		customerService.saveCustomer(customer);

		Customer foundCustomer = customerService.findById(customer.getId());

		System.out.println("Found customer: " + foundCustomer);

		foundCustomer.setName("Jane Doe");

		customerService.saveCustomer(foundCustomer);

		for (Customer c : customerService.findAll()) {
			System.out.println("Found customer by findAll(): " + c);
		}
	}
}
