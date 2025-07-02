package com.intercom.spring.service;

import com.intercom.spring.dto.MessageResponse;
import com.intercom.spring.mapper.MessageMapper;
import com.intercom.spring.model.Message;
import com.intercom.spring.model.User;
import com.intercom.spring.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    protected OpenAIService openAIService;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper, OpenAIService openAIService) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.openAIService = openAIService;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    public List<MessageResponse> getMessagesByUserId(Long userId) {
        log.info("action=getMessagesByUserId; message=Fetching messages for user; userId={}", userId);
        List<Message> messages = messageRepository.findByUserId(userId);
        List<MessageResponse> responseMessages = new ArrayList<>();

        for (Message message : messages) {
            MessageResponse responseMessage = messageMapper.messageToMessageResponse(message);
            responseMessage.setSentiment(openAIService.checkSentiment(message.getContent()));
            responseMessages.add(responseMessage);

        }

        return responseMessages;
    }

    /**
     * Save a user message for a given userId.
     */
    public void saveUserMessage(Long userId, String content) {
        Message message = new Message();
        User user = new User();
        user.setId(userId);
        message.setUser(user);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());
        message.setMessageRead(true);
        messageRepository.save(message);
    }
}
