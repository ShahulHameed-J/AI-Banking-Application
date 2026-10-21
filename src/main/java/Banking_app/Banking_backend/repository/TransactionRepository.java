package Banking_app.Banking_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Banking_app.Banking_backend.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findByFromAccountOrToAccountOrderByTransactionDateDesc(String fromAccount, String toAccount);
}
