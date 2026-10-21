package Banking_app.Banking_backend.service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Banking_app.Banking_backend.dto.AuthResponse;
import Banking_app.Banking_backend.dto.LoginRequest;
import Banking_app.Banking_backend.dto.RegisterRequest;
import Banking_app.Banking_backend.entity.Account;
import Banking_app.Banking_backend.entity.User;
import Banking_app.Banking_backend.repository.AccountRepository;
import Banking_app.Banking_backend.repository.UserRepository;
import Banking_app.Banking_backend.security.JwtService;

@Service // this indicate springboot to tell this file is for main logic
public class AuthService {
	
	private final UserRepository userRepository;
	private final AccountRepository accountRepository;
	private final JwtService jwtService;
	
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public AuthService(UserRepository userRepository, AccountRepository accountRepository, JwtService jwtService) {
		super();
		this.userRepository = userRepository;
		this.accountRepository = accountRepository;
		this.jwtService = jwtService;
	}

	// Register
	public AuthResponse register(RegisterRequest request) {
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already registered");
		}
		
		String hashedPassword = encoder.encode(request.getPassword());
		
		User user = new User(request.getName(), request.getEmail(), request.getPhone(), request.getAddress(), hashedPassword);
		
		user = userRepository.save(user);
		
		Account account = new Account(genearteAccountNumber(), user);
		account.setBalance(BigDecimal.ZERO);
		accountRepository.save(account);
		
		String token = jwtService.generateToken(user.getEmail());
		
		return new AuthResponse(user.getId(), user.getName(), user.getEmail(), token);
	}
	
	// login
	public AuthResponse login(LoginRequest request) {
		User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid username or password"));
		if(!encoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Incorrect Password");
		}
		String token = jwtService.generateToken(user.getEmail());
		return new AuthResponse(user.getId(), user.getName(), user.getEmail(), token);
	}
	
	private String genearteAccountNumber() {
		String accountNumber;
		do {
			long number = ThreadLocalRandom.current().nextLong(
					1_000_000_000L, 9_999_999_999L);
			accountNumber = String.valueOf(number);
		}while(accountRepository.findByAccountNumber(accountNumber).isPresent());
		return accountNumber;
	}
	
}
