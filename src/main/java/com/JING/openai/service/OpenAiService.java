package com.JING.openai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class OpenAiService {

    private static final Logger logger = LoggerFactory.getLogger(OpenAiService.class);

    @Value("${openai.api.key:mock}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String askOpenAi(String prompt) {
        logger.info("Received prompt: {}", prompt);

        if (apiKey.equals("mock")) {
            return getMockResponse(prompt);
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "gpt-3.5-turbo");
            requestBody.put("messages", List.of(Map.of("role", "user", "content", prompt)));

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "https://api.openai.com/v1/chat/completions",
                    request,
                    Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    String result = message.get("content").toString().trim();
                    logger.info("OpenAI response: {}", result);
                    return result;
                }
            }

        } catch (HttpClientErrorException.TooManyRequests ex) {
            logger.warn("Quota exceeded. Falling back to mock response.");
        } catch (Exception ex) {
            logger.error("Error calling OpenAI: {}", ex.getMessage());
        }

        return getMockResponse(prompt);
    }

    private String getMockResponse(String prompt) {
        return switch (prompt.toLowerCase()) {
            case "what is spring boot?" -> "Spring Boot is a lightweight Java framework used to build web applications quickly.";
            case "who are you?" -> "I'm a simulated AI assistant. The real one is taking a break 😅";
            default -> "This is a mock response. Your prompt was: \"" + prompt + "\"";
        };
    }
}
