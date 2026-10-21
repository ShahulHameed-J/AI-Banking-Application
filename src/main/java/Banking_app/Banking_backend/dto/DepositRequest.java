package Banking_app.Banking_backend.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DepositRequest {
	@NotBlank
	private String accountNumber;
	@NotNull @DecimalMin(value = "0.1", message = "Amount should be greater than zero")
	private BigDecimal amount;
	
	// Getters and Setters
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
