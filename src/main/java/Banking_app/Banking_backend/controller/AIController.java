package Banking_app.Banking_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Banking_app.Banking_backend.dto.AIRequest;
import Banking_app.Banking_backend.dto.AIResponse;
import Banking_app.Banking_backend.service.AIService;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "http://localhost:5173")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/chat")
    public ResponseEntity<AIResponse> chat(
            @RequestBody AIRequest request) {

        String response = aiService.processMessage(
                request.getMessage(),
                request.getAccountNumber(),
                request.getAccountType(),
                request.getBalance()
        );

        return ResponseEntity.ok(
                new AIResponse(response)
        );
    }
}