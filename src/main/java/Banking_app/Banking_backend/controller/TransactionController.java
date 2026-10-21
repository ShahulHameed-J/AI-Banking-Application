package Banking_app.Banking_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Banking_app.Banking_backend.dto.DepositRequest;
import Banking_app.Banking_backend.dto.TransferRequest;
import Banking_app.Banking_backend.dto.WithdrawRequest;
import Banking_app.Banking_backend.entity.Transaction;
import Banking_app.Banking_backend.service.TransactionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class TransactionController {
	private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/accounts/deposit")
    public ResponseEntity<Transaction> deposit(@Valid @RequestBody DepositRequest request) {
        return ResponseEntity.ok(transactionService.deposit(request));
    }

    @PostMapping("/accounts/withdraw")
    public ResponseEntity<Transaction> withdraw(@Valid @RequestBody WithdrawRequest request) {
        return ResponseEntity.ok(transactionService.withdraw(request));
    }

    @PostMapping("/accounts/transfer")
    public ResponseEntity<Transaction> transfer(@Valid @RequestBody TransferRequest request) {
        return ResponseEntity.ok(transactionService.transfer(request));
    }

    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getHistory(accountNumber));
    }
}
