package Banking_app.Banking_backend.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransferRequest {
	@NotBlank
	private String fromAccount;
	@NotBlank
	private String toAccount;
	@NotNull @DecimalMin(value = "0.01", message = "Amount should be greater than 0")
	private BigDecimal amount;
	
	// Getters and Setters
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
