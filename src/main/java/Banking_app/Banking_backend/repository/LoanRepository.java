package Banking_app.Banking_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Banking_app.Banking_backend.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
	List<Loan> findByUserId(Long userId);
}
