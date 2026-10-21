package Banking_app.Banking_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String transactionType;
	private BigDecimal amount;
	private String fromAccount;
	private String toAccount;
	private LocalDateTime transactionDate = LocalDateTime.now();
	
	public Transaction(String transactionType, BigDecimal amount, String fromAccount, String toAccount,
			LocalDateTime transactionDate) {
		super();
		this.transactionType = transactionType;
		this.amount = amount;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transactionDate = transactionDate;
	}
	
	public Transaction() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
}
