package Banking_app.Banking_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Banking_app.Banking_backend.entity.LoanScheme;

public interface LoanSchemeRepository extends JpaRepository<LoanScheme, Long>{
	Optional<LoanScheme> findByLoanType(String loanType);
	
}
