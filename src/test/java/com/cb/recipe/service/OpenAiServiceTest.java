package com.cb.recipe.service;

import com.cb.recipe.client.OpenAiClient;
import com.cb.recipe.util.UnitTest;
import com.github.benmanes.caffeine.cache.Cache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
class OpenAiServiceTest extends UnitTest {
    @Mock
    private OpenAiClient client;

    @Mock
    private Cache<String, String> imageResponseCache;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private OpenAiService openAiService;

    @Test
    void givenValidFileWithNoCacheHit_whenProcessImage_thenExpectedResult() throws IOException {
        Mockito.when(file.getOriginalFilename())
                .thenReturn("testFile.png");
        Mockito.when(file.getBytes())
                .thenReturn("Dummy Bytes".getBytes(StandardCharsets.UTF_8));
        Mockito.when(imageResponseCache.getIfPresent("testFile.png"))
                .thenReturn(null);
        Mockito.when(client.processImage(Mockito.anyString()))
                .thenReturn(mockedImageResponse());

        String result = openAiService.processImage(file);

        snapshot(result);
    }

    @Test
    void givenValidFileInCache_whenProcessImage_thenExpectedResultFromCache() {
        Mockito.when(file.getOriginalFilename())
                .thenReturn("cachedFile.png");
        Mockito.when(imageResponseCache.getIfPresent("cachedFile.png"))
                .thenReturn("apple, banana, tomato");

        String result = openAiService.processImage(file);

        snapshot(result);
    }

    private String mockedImageResponse() {
        return """
                {
                  "choices": [
                    {
                      "message": {
                        "content": "apple, banana, tomato, (it might be Harald's fault)"
                      }
                    }
                  ]
                }
                """;
    }
}