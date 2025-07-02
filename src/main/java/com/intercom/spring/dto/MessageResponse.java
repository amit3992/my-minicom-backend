package com.intercom.spring.dto;

public class MessageResponse {
    private Long messageId;
    private Long userId;
    private String content;
    private String sentiment;
    private String createdAt;
    private Boolean messageRead;

    public Long getMessageId() { return messageId; }
    public void setMessageId(Long messageId) { this.messageId = messageId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getSentiment() { return sentiment; }
    public void setSentiment(String sentiment) { this.sentiment = sentiment; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public Boolean getMessageRead() { return messageRead; }
    public void setMessageRead(Boolean messageRead) { this.messageRead = messageRead; }
}
