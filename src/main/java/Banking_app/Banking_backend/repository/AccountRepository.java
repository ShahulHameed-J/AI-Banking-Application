package Banking_app.Banking_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Banking_app.Banking_backend.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByAccountNumber(String accountNumber);
	List<Account> findByUserId(Long userId);
}
