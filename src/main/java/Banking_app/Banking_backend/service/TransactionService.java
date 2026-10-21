package Banking_app.Banking_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import Banking_app.Banking_backend.dto.DepositRequest;
import Banking_app.Banking_backend.dto.TransferRequest;
import Banking_app.Banking_backend.dto.WithdrawRequest;
import Banking_app.Banking_backend.entity.Account;
import Banking_app.Banking_backend.entity.Transaction;
import Banking_app.Banking_backend.repository.AccountRepository;
import Banking_app.Banking_backend.repository.TransactionRepository;
import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	
	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	
	public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
		super();
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}
	
	public Transaction deposit(DepositRequest request) {
		Account account = findAccount(request.getAccountNumber());
		account.setBalance(account.getBalance().add(request.getAmount()));
		accountRepository.save(account);
		
		Transaction transaction = new Transaction("DEPOSIT", request.getAmount(), null, request.getAccountNumber(), LocalDateTime.now());
		transactionRepository.save(transaction);
		return transaction;
	}
	
	public Transaction withdraw(WithdrawRequest request) {
		Account account = findAccount(request.getAccountNumber());
		if(account.getBalance().compareTo(request.getAmount()) < 0) {
			throw new RuntimeException("Insufficient Balance");
		}
		account.setBalance(account.getBalance().subtract(request.getAmount()));
		accountRepository.save(account);
		
		Transaction transaction = new Transaction("WITHDRAWAL", request.getAmount(), request.getAccountNumber(), null, LocalDateTime.now());
		transactionRepository.save(transaction);
		return transaction;
	}
	
	@Transactional
	public Transaction transfer(TransferRequest request) {
		Account sender = findAccount(request.getFromAccount());
		Account receiver = findAccount(request.getToAccount());
		
		if(sender.getBalance().compareTo(request.getAmount()) < 0) {
			throw new RuntimeException("Insufficient Balance");
		}
		sender.setBalance(sender.getBalance().subtract(request.getAmount()));
		receiver.setBalance(receiver.getBalance().add(request.getAmount()));
		
		accountRepository.save(sender);
		accountRepository.save(receiver);
		
		Transaction transaction = new Transaction("TRANSFER", request.getAmount(), sender.getAccountNumber(), receiver.getAccountNumber(), LocalDateTime.now());
		
		transactionRepository.save(transaction);
		return transaction;
	}
	
	public List<Transaction> getHistory(String accountNumber){
		return transactionRepository.findByFromAccountOrToAccountOrderByTransactionDateDesc(accountNumber, accountNumber);
	}
	
	private Account findAccount(String accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Account not Found: " + accountNumber));		
	}
}
