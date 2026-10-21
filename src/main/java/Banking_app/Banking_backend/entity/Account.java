package Banking_app.Banking_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String accountNumber;
	private String accountType = "Savings";
	private BigDecimal balance;
	private LocalDateTime createdate = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Account(String accountNumber, User user) {
		super();
		this.accountNumber = accountNumber;
		this.user = user;
	}
	

	public Account() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
