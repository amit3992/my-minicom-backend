package com.intercom.spring.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OpenAIService {
    private final ChatClient chatClient;

    public OpenAIService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateAnswerFromDocs(List<Document> documents, String userQuery) {
        var inlined = documents.stream().map(Document::getContent).collect(Collectors.joining(System.lineSeparator()));
        var similarDocsMessage = new SystemPromptTemplate(
            "You are an expert in Intercom API developer documentation. " +
            "Given the following context, answer the user's query in a concise, point-wise manner. " +
            "If possible, provide numbered or bulleted points. " +
            "Only use information from the provided context.\n" +
            "\n" +
            "Example:\n" +
            "Context:\nHow can I authenticate API requests to Intercom?\n" +
            "User Query: How do I authenticate with the Intercom API?\n" +
            "Answer:\n1. Use OAuth 2.0 to authenticate API requests.\n2. Obtain an access token by following Intercom's OAuth flow.\n3. Include the token in the Authorization header as 'Bearer <token>' in your requests.\n" +
            "\n" +
            "Context:\n{documents}"
        ).createMessage(Map.of("documents", inlined));
        var userMessage = new UserMessage(userQuery);
        Prompt prompt = new Prompt(List.of(similarDocsMessage, userMessage));
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}
