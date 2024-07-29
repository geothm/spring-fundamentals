package ro.wantsome.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.wantsome.databases.domain.Customer;
import ro.wantsome.databases.domain.CustomerRepository;

import javax.annotation.PostConstruct;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Transactional
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
}
