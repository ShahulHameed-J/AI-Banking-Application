package Banking_app.Banking_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Banking_app.Banking_backend.dto.AuthResponse;
import Banking_app.Banking_backend.dto.LoginRequest;
import Banking_app.Banking_backend.dto.RegisterRequest;
import Banking_app.Banking_backend.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	 private final AuthService authService;

	 public AuthController(AuthService authService) {
		 this.authService = authService;
	 }

	    // @Valid triggers the checks written in RegisterRequest (@NotBlank etc.)
	    // Spring automatically converts the incoming JSON body into a RegisterRequest object.
	 @PostMapping("/register")
	 public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
		 return ResponseEntity.ok(authService.register(request));
	 }

	 @PostMapping("/login")
	 public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
		 return ResponseEntity.ok(authService.login(request));
	 }
}
