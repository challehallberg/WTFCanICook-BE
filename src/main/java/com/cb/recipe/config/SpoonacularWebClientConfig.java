package com.cb.recipe.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
@Slf4j
public class SpoonacularWebClientConfig {
    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Bean
    @Qualifier("spoonacularWebClient")
    public WebClient spoonacularWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.spoonacular.com")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                // Add a filter to append the API key as a query parameter to every request
                .filter((request, next) -> {
                    final URI originalUri = request.url();

                    final URI newUri = UriComponentsBuilder.fromUri(originalUri)
                            .queryParam("apiKey", apiKey)
                            .build(true)
                            .toUri();

                    final ClientRequest newRequest = ClientRequest.from(request)
                            .url(newUri)
                            .build();

                    return next.exchange(newRequest);
                })
                .build();
    }
}