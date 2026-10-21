package Banking_app.Banking_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Banking_app.Banking_backend.dto.LoanRequest;
import Banking_app.Banking_backend.entity.Loan;
import Banking_app.Banking_backend.entity.LoanScheme;
import Banking_app.Banking_backend.entity.User;
import Banking_app.Banking_backend.repository.LoanRepository;
import Banking_app.Banking_backend.repository.LoanSchemeRepository;
import Banking_app.Banking_backend.repository.UserRepository;

@Service
public class LoanService {
	private final LoanRepository loanRepository;
	private final LoanSchemeRepository loanSchemeRepository;
	private final UserRepository userRepository;
	public LoanService(LoanRepository loanRepository, LoanSchemeRepository loanSchemeRepository,
			UserRepository userRepository) {
		super();
		this.loanRepository = loanRepository;
		this.loanSchemeRepository = loanSchemeRepository;
		this.userRepository = userRepository;
	}

	// for display loan schemes 
	public List<LoanScheme> getAllScheme(){
		return loanSchemeRepository.findAll();
	}
	
	// apply for loan
	public Loan applyForLoan(LoanRequest request) {
		User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
		LoanScheme scheme = loanSchemeRepository.findByLoanType(request.getLoanType()).orElseThrow(() -> new RuntimeException("No loan scheme available"));
		
		Loan loan = new Loan(request.getLoanType(), request.getAmount(), request.getDurationYears(), request.getMonthlyIncome(), user);
		boolean amountAllowed = request.getAmount().compareTo(scheme.getMaxAmount()) <= 0;
		boolean incomeSufficient = request.getMonthlyIncome().compareTo(scheme.getMinMonthlyIncome()) >= 0;
		boolean loanCompletion = request.getDurationYears() <= scheme.getMaxTenureYears();
		
		if(amountAllowed && incomeSufficient && loanCompletion) {
			loan.setStatus("APPROVED");
		}
		else {
			loan.setStatus("REJECTED");
		}
		return loanRepository.save(loan);
	}
	
	// To get all loans of the user
	public List<Loan> getLoansForUser(String email){
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not Found"));
		return loanRepository.findByUserId(user.getId());
	}
}
