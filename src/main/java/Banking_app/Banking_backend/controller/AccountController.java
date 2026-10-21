package Banking_app.Banking_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Banking_app.Banking_backend.entity.Account;
import Banking_app.Banking_backend.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
//@CrossOrigin(origin = )
public class AccountController {
	private final AccountService accountService;
	
	public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // No login token to read anymore - the frontend just tells us
    // which user it's asking about, e.g. GET /api/accounts/my?email=a@b.com
    @GetMapping("/my")
    public ResponseEntity<List<Account>> getMyAccounts(@RequestParam String email) {
        return ResponseEntity.ok(accountService.getAccountsForUser(email));
    }
}