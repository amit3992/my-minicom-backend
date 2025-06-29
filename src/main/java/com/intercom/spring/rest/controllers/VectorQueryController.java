package com.intercom.spring.rest.controllers;

import com.intercom.spring.model.UserQuery;
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

    @PostMapping
    public Map<String, String> chat(@RequestBody UserQuery userQuery) {
        log.info("action=chat");
        String userMessage = userQuery.getMessage();
        List<Document> documents = vectorStore.similaritySearch(userMessage);
        String generation = openAIService.generateAnswerFromDocs(documents, userMessage);
        return Map.of("reply", generation);
    }
}
