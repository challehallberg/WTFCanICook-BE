package com.cb.recipe.service;

import com.cb.recipe.client.OpenAiClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OpenAiService {
    private final OpenAiClient client;
    private final Cache<String, String> imageResponseCache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public OpenAiService(final OpenAiClient client) {
        this.client = client;
    }

    public String processImage(final MultipartFile file) {
        final String fileName = file.getOriginalFilename();
        final String cachedIngredients = imageResponseCache.getIfPresent(fileName);

        if (cachedIngredients != null) {
            log.info("File already cached, retrieving cached ingredients");
            return cachedIngredients;
        }
        try {
            log.info("Processing image");
            final String response = client.processImage(getRequestBody(encodeImageToBase64(file)));

            final String ingredients = extractDataFromResponse(response);

            imageResponseCache.put(fileName, ingredients);

            return ingredients;
        } catch (Exception e) {
            log.warn(e.getMessage());
            return "";
        }
    }

    private String extractDataFromResponse(final String response) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();

            // Parse JSON response
            final JsonNode rootNode = objectMapper.readTree(response);

            return rootNode
                    .path("choices")     // Navigate to "choices"
                    .get(0)                 // Get the first choice
                    .path("message")     // Navigate to "message"
                    .path("content")     // Extract "content"
                    .asText();
        } catch (Exception e) {
            log.warn("Could not extract data from response");
            return "";
        }
    }

    private String getRequestBody(final String base64Image) {
        return """
                {
                    "model": "gpt-4o-mini",
                    "messages": [
                        {
                            "role": "user",
                            "content": [
                                {
                                    "type": "text",
                                    "text": "Analyze the attached image and identify all food ingredients present in it. List them in the format: ingredient1, ingredient2, ... If there are none, return an empty String"
                                },
                                {
                                    "type": "image_url",
                                    "image_url": {
                                        "url": "data:image/jpeg;base64,%s"
                                    }
                                }
                            ]
                        }
                    ]
                }
                """.formatted(base64Image);
    }

    private String encodeImageToBase64(final MultipartFile image) throws IOException {
        final byte[] fileBytes = image.getBytes();
        return Base64.getEncoder().encodeToString(fileBytes);
    }
}
