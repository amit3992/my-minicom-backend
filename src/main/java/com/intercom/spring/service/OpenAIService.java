package com.intercom.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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

    /**
     * Checks the sentiment of a message using the LLM. Returns one of: POSITIVE, NEUTRAL, NEGATIVE.
     * @param messageContent the message to analyze
     * @return sentiment as a String (POSITIVE, NEUTRAL, or NEGATIVE)
     */
    public String checkSentiment(String messageContent) {
        String systemPrompt = "You are a helpful assistant. Analyze the sentiment of the following message. Respond with only one word: POSITIVE, NEUTRAL, or NEGATIVE. Do not provide any explanation.";
        try {
            var systemMessage = new SystemPromptTemplate(systemPrompt).createMessage(Map.of());
            var userMessage = new UserMessage(messageContent);
            Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
            String response = chatClient.call(prompt).getResult().getOutput().getContent();
            // Normalize and validate response
            response = response.trim().toUpperCase();
            if (!response.equals("POSITIVE") && !response.equals("NEUTRAL") && !response.equals("NEGATIVE")) {
                return "NEUTRAL";
            }
            return response;
        } catch (Exception e) {
            // Log the exception (use your preferred logging framework)\
            log.error("Error during sentiment analysis", e);
            return "NEUTRAL";
        }
    }
}
