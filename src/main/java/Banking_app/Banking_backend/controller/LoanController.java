package Banking_app.Banking_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Banking_app.Banking_backend.dto.LoanRequest;
import Banking_app.Banking_backend.entity.Loan;
import Banking_app.Banking_backend.entity.LoanScheme;
import Banking_app.Banking_backend.service.LoanService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
	private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // The bank's lending rules - shown to the user before they apply
    @GetMapping("/schemes")
    public ResponseEntity<List<LoanScheme>> getSchemes() {
        return ResponseEntity.ok(loanService.getAllScheme());
    }

    @PostMapping
    public ResponseEntity<Loan> apply(@Valid @RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.applyForLoan(request));
    }

    // e.g. GET /api/loans/my?email=a@b.com
    @GetMapping("/my")
    public ResponseEntity<List<Loan>> myLoans(@RequestParam String email) {
        return ResponseEntity.ok(loanService.getLoansForUser(email));
    }
}
