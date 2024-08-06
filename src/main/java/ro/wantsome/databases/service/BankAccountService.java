package ro.wantsome.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.wantsome.databases.domain.BankAccount;
import ro.wantsome.databases.domain.BankAccountRepository;

import java.util.List;

@Service
public class BankAccountService {

	private final BankAccountRepository bankAccountRepository;

	public BankAccountService(BankAccountRepository bankAccountRepository) {
		this.bankAccountRepository = bankAccountRepository;
	}

	@Transactional
	public void save(BankAccount bankAccount) {
		bankAccountRepository.save(bankAccount);
	}

	public BankAccount findById(Long id) {
		return bankAccountRepository.findById(id);
	}

	public List<BankAccount> findAll() {
		return bankAccountRepository.findAll();
	}
}