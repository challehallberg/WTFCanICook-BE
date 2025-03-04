package com.cb.recipe.config;

import com.cb.recipe.model.instruction.Instruction;
import com.cb.recipe.model.recipe.Recipe;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    @Qualifier("imageResponseCache")
    public Cache<String, String> imageResponseCache() {
         return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    @Bean
    @Qualifier("recipeCache")
    public Cache<String, List<Recipe>> recipeCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    @Bean
    @Qualifier("instructionsCache")
    public Cache<Integer, List<Instruction>> instructionsCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }
}
