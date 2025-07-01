package com.intercom.spring.rest.controllers;

import com.intercom.spring.model.Message;
import com.intercom.spring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Optional<Message> message = messageService.getMessageById(id);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Message> getMessagesByUserId(@PathVariable Long userId) {
        log.info("action=getMessagesByUserId; message=Fetching messages for user; userId={}", userId);
        return messageService.getMessagesByUserId(userId);
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage) {
        Optional<Message> existing = messageService.getMessageById(id);
        if (existing.isPresent()) {
            Message msg = existing.get();
            msg.setContent(updatedMessage.getContent());
            msg.setMessageRead(updatedMessage.getMessageRead());
            msg.setCreatedAt(updatedMessage.getCreatedAt());
            msg.setUpdatedAt(updatedMessage.getUpdatedAt());
            msg.setUser(updatedMessage.getUser());
            return ResponseEntity.ok(messageService.saveMessage(msg));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        if (messageService.getMessageById(id).isPresent()) {
            messageService.deleteMessage(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
