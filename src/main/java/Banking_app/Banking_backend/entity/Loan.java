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
@Table(name="loan")
public class Loan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String loanType;
	private BigDecimal amount;
	private int durationYears;
	private BigDecimal monthlyIncome;
	private String status = "PENDING";
	private LocalDateTime appliedDate = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Loan(String loanType, BigDecimal amount, int durationYears, BigDecimal monthlyIncome, User user) {
		super();
		this.loanType = loanType;
		this.amount = amount;
		this.durationYears = durationYears;
		this.monthlyIncome = monthlyIncome;
		this.user = user;
	}
	

	public Loan() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getDurationYears() {
		return durationYears;
	}

	public void setDurationYears(int durationYears) {
		this.durationYears = durationYears;
	}

	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDateTime appliedDate) {
		this.appliedDate = appliedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
