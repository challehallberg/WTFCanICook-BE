package com.cb.recipe.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void logForMe(Object toBeLogged) {
        try {
            log.info(objectMapper.writeValueAsString(toBeLogged));
        } catch (JsonProcessingException e) {
            log.error("Error logging object.");
        }
    }
}
