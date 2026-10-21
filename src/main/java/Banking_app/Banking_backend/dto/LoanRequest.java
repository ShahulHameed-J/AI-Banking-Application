package Banking_app.Banking_backend.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoanRequest {
	
	@NotBlank @Email(message = "enter valid email")
	private String email;
	
	@NotBlank
	private String loanType;
	
	@NotNull @DecimalMin(value = "1000", message = "loan amount minimum 1000")
	private BigDecimal amount;
	
	@NotBlank 
	private int durationYears;
	private BigDecimal monthlyIncome;
	
	// Getters and Setters
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
}
