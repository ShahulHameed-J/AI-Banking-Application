package Banking_app.Banking_backend.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WithdrawRequest {
	
	@NotBlank
	private String accountNumber;
	@NotNull @DecimalMin(value = "0.01", message = "Withdraw Amount should be greater than 0")
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