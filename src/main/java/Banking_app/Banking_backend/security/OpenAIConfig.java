package Banking_app.Banking_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;

@Configuration
public class OpenAIConfig {

    @Bean
    public OpenAIClient openAIClient() {
        return OpenAIOkHttpClient.fromEnv();
    }
}