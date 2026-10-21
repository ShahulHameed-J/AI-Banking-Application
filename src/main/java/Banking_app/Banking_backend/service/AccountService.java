package Banking_app.Banking_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Banking_app.Banking_backend.entity.Account;
import Banking_app.Banking_backend.entity.User;
import Banking_app.Banking_backend.repository.AccountRepository;
import Banking_app.Banking_backend.repository.UserRepository;

@Service
public class AccountService {
	private final UserRepository userRepository;
	private final AccountRepository accountRepository;
	
	public AccountService(UserRepository userRepository, AccountRepository accountRepository) {
		super();
		this.userRepository = userRepository;
		this.accountRepository = accountRepository;
	}
	
	public List<Account> getAccountsForUser(String email){
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		return accountRepository.findByUserId(user.getId());
	}
}