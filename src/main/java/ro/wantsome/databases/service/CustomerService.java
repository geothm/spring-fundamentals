package ro.wantsome.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.wantsome.databases.domain.Customer;
import ro.wantsome.databases.domain.CustomerJpaRepository;
import ro.wantsome.databases.domain.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	private final CustomerJpaRepository customerJpaRepository;

	public CustomerService(CustomerRepository customerRepository,
			CustomerJpaRepository customerJpaRepository) {
		this.customerRepository = customerRepository;
		this.customerJpaRepository = customerJpaRepository;
	}

	@Transactional
	public void saveCustomer(Customer customer) {
		customerJpaRepository.save(customer);

		//save new Employee responsible for Customer
	}

	public Customer findById(Long id) {
		return customerJpaRepository.findById(id)
				.orElse(null);
	}

	public List<Customer> findAll() {
		return customerJpaRepository.findAll();
	}
}
