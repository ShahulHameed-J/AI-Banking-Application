package Banking_app.Banking_backend.service;

import org.springframework.stereotype.Service;

import com.openai.client.OpenAIClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

@Service
public class AIService {

    private final OpenAIClient openAIClient;

    public AIService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    public String processMessage(
            String message,
            String accountNumber,
            String accountType,
            String balance) {

        String prompt = """
                You are an AI Banking Assistant.

                You are helping a customer with their bank account.

                Customer account information:
                Account Number: %s
                Account Type: %s
                Current Balance: ₹%s

                Customer's message:
                %s

                Rules:

                1. Answer banking questions clearly and simply.
                2. If the customer asks for their balance, provide the current balance.
                3. If the customer asks about their account, use the account information provided.
                4. Do not invent transactions, account information, or bank policies.
                5. Do not claim that money has been transferred, deposited, or withdrawn.
                6. Actual banking transactions are handled separately by the banking system.
                7. Never expose passwords, JWT tokens, or other authentication information.
                8. If the customer asks to transfer, deposit, or withdraw money,
                   explain that they should use the corresponding banking operation.
                9. Keep answers concise and friendly.

                Answer the customer's message.
                """.formatted(
                accountNumber,
                accountType,
                balance,
                message
        );

        ResponseCreateParams params =
                ResponseCreateParams.builder()
                        .input(prompt)
                        .model(ChatModel.GPT_5_2)
                        .build();

        Response response = openAIClient
                .responses()
                .create(params);

        return response.output()
                .stream()
                .flatMap(item -> item.message().stream())
                .flatMap(messageItem -> messageItem.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(outputText -> outputText.text())
                .reduce("", String::concat);
    }
}