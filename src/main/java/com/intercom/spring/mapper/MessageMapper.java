package com.intercom.spring.mapper;

import com.intercom.spring.model.Message;
import com.intercom.spring.dto.MessageResponse;
import com.intercom.spring.service.OpenAIService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {


    @Mapping(target = "messageId", source = "id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "sentiment", ignore = true)
    public abstract MessageResponse messageToMessageResponse(Message message);
}
