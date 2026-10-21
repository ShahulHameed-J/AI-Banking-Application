package Banking_app.Banking_backend.dto;

public class AIRequest {

    private String message;
    private String accountNumber;
    private String accountType;
    private String balance;

    public AIRequest() {
    }

    public AIRequest(
            String message,
            String accountNumber,
            String accountType,
            String balance) {

        this.message = message;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}