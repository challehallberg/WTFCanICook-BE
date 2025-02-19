package com.cb.recipe.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OpenAiClient {
    private final WebClient client;

    public OpenAiClient(@Qualifier("openAiWebClient") final WebClient webClient) {
        this.client = webClient;
    }

    public String processImage(final String requestBody) {
        //TODO(CB): Add retryability
        return client.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
