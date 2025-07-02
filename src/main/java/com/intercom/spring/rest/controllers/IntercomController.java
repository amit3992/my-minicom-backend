package com.intercom.spring.rest.controllers;

import io.intercom.api.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/intercom/api")
public class IntercomController {

    // Example: Send a message from an admin to a user and get conversations
    @PostMapping("/chat")
    public Map<String, String> chat(@RequestParam("user_id") String userId,
                                    @RequestParam("message") String message) {
        log.info("action=intercomChat; userId={}", userId);
        try {
            // Create or fetch the user
            User user = new User()
                    .setUserId(userId);

            // Test to send a message as an admin
            Admin admin = new Admin().setId("8476757");
            AdminReply adminReply = new AdminReply(admin);
            adminReply.setBody("These apples are healthsome");
            Conversation.reply("215469698622012", adminReply);
//            AdminMessage adminMessage = new AdminMessage()
//                    .setAdmin(admin)
//                    .setUser(user)
//                    .setSubject("This Land")
//                    .setBody("Har har har! Mine is an evil laugh!")
//                    .setMessageType("inapp")
//                    .setTemplate("plain");
//            Conversation.create(adminMessage);

            return Map.of("status", "Message sent to Intercom");
        } catch (Exception e) {
            log.error("Intercom chat error", e);
            return Map.of("error", "Failed to send message to Intercom");
        }
    }

    @GetMapping("/admins")
    public Object listAdmins() {
        try {
            AdminCollection admins = Admin.list();
            return admins;
        } catch (Exception e) {
            log.error("Failed to list Intercom admins", e);
            return Map.of("error", "Failed to list admins");
        }
    }

    @PostMapping("/user")
    public Object createUser(@RequestParam("user_id") String userId,
                             @RequestParam(value = "email", required = false) String email) {
        try {
            User user = new User().setUserId(userId);
            if (email != null) user.setEmail(email);
            return User.create(user);
        } catch (Exception e) {
            log.error("Failed to create Intercom user", e);
            return Map.of("error", "Failed to create user");
        }
    }

    @PostMapping("/user/message")
    public Object sendUserMessage(@RequestHeader("user-id") String userId,
                                  @RequestBody com.intercom.spring.dto.IntercomUserMessageRequest req) {
        log.info("action=intercomUserMessage; userId={}", userId);
        try {
            User user = new User().setUserId(userId);
            UserMessage userMessage = new UserMessage()
                    .setUser(user)
                    .setBody(req.getMessage());
            UserMessage userMessageResponse = Conversation.create(userMessage);
            return Map.of("status", "Message sent to Intercom as user");
        } catch (Exception e) {
            log.error("Failed to send user message to Intercom", e);
            return Map.of("error", "Failed to send message as user");
        }
    }
}
