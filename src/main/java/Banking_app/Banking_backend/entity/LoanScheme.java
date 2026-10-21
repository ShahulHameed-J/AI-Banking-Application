package Banking_app.Banking_backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="loan_scheme")
public class LoanScheme {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String loanType;
	private BigDecimal interstRate;
	private BigDecimal maxAmount;
	private BigDecimal minMonthlyIncome;
	private int maxTenureYears;
	public LoanScheme(String loanType, BigDecimal interstRate, BigDecimal maxAmount, BigDecimal minMonthlyIncome,
			int maxTenureYears) {
		super();
		this.loanType = loanType;
		this.interstRate = interstRate;
		this.maxAmount = maxAmount;
		this.minMonthlyIncome = minMonthlyIncome;
		this.maxTenureYears = maxTenureYears;
	}
	
	public LoanScheme() {
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
	public BigDecimal getInterstRate() {
		return interstRate;
	}
	public void setInterstRate(BigDecimal interstRate) {
		this.interstRate = interstRate;
	}
	public BigDecimal getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}
	public BigDecimal getMinMonthlyIncome() {
		return minMonthlyIncome;
	}
	public void setMinMonthlyIncome(BigDecimal minMonthlyIncome) {
		this.minMonthlyIncome = minMonthlyIncome;
	}
	public int getMaxTenureYears() {
		return maxTenureYears;
	}
	public void setMaxTenureYears(int maxTenureYears) {
		this.maxTenureYears = maxTenureYears;
	}
}
