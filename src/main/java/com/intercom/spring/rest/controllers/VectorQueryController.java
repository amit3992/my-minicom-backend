package com.intercom.spring.rest.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.document.Document;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class VectorQueryController {
    private final VectorStore vectorStore;

    private final ChatClient chatClient;

    @PostMapping
    public Map<String, String> chat(@RequestBody UserQuery userQuery) {
        String query = userQuery.getQuery();
        List<Document> documents = vectorStore.similaritySearch(query);
        var inlined = documents.stream().map(Document::getContent).collect(Collectors.joining(System.lineSeparator()));
        var similarDocsMessage = new SystemPromptTemplate(
            "You are an expert in Intercom developer documentation. " +
            "Given the following context, answer the user's query in a concise, point-wise manner. " +
            "If possible, provide numbered or bulleted points. " +
            "Only use information from the provided context.\n" +
            "Context:\n{documents}"
        ).createMessage(Map.of("documents", inlined));
        var userMessage = new UserMessage(userQuery.getQuery());
        Prompt prompt = new Prompt(List.of(similarDocsMessage, userMessage));
        return Map.of("generation", chatClient.call(prompt).getResult().getOutput().getContent());
    }
}
