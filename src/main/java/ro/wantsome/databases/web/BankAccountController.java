package ro.wantsome.databases.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.wantsome.databases.domain.BankAccount;
import ro.wantsome.databases.service.BankAccountService;

@Controller
public class BankAccountController {

	private final BankAccountService bankAccountService;

	public BankAccountController(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}

	@GetMapping("/newBankAccount")
	public String bankAccountForm(Model model) {
		model.addAttribute("bankAccount", new BankAccount());

		return "bankaccount/bankAccountForm";
	}

	@PostMapping("/accounts")
	public String submitBankAccount(Model model, @ModelAttribute BankAccount bankAccount) {
		bankAccountService.save(bankAccount);

		model.addAttribute("accounts", bankAccountService.findAll());

		return "bankaccount/bankAccountTable";
	}

	@GetMapping("/accounts")
	public String getBankAccounts(Model model) {
		model.addAttribute("accounts", bankAccountService.findAll());

		return "bankaccount/bankAccountTable";
	}
}
