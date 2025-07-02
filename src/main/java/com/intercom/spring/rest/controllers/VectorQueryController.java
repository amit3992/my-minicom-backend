package com.intercom.spring.rest.controllers;

import com.intercom.spring.model.UserQuery;
import com.intercom.spring.service.MessageService;
import com.intercom.spring.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class VectorQueryController {
    private final VectorStore vectorStore;
    private final OpenAIService openAIService;
    private final MessageService messageService;

    @PostMapping
    public Map<String, String> chat(@RequestHeader("user-id") Long userId, @RequestBody UserQuery userQuery) {
        log.info("action=chat; message=Chatting with user; userId={}", userId);
        try {
            String userMessage = userQuery.getMessage();
            // Save the message for this user
            messageService.saveUserMessage(userId, userMessage);
            List<Document> documents = vectorStore.similaritySearch(userMessage);
            String generation = openAIService.generateAnswerFromDocs(documents, userMessage);
            return Map.of("reply", generation);
        } catch (Exception e) {
            log.error("Error in chat endpoint for userId={}: {}", userId, e.getMessage(), e);
            return Map.of("error", "An error occurred while processing your request. Please try again later.");
        }
    }
}
