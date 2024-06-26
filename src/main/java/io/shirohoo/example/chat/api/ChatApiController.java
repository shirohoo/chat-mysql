package io.shirohoo.example.chat.api;

import lombok.RequiredArgsConstructor;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

import io.shirohoo.example.chat.service.ChatService;

@RestController
@RequiredArgsConstructor
class ChatApiController {
    private final ChatService chatService;

    @PostMapping("/api/v1/chat")
    Map<String, String> createChat(@RequestBody CreateChatRequest request) {
        var chat = chatService.createChat(request.hostId, request.topic, request.password);

        return Map.of("chatId", chat.getId().toString());
    }

    private record CreateChatRequest(UUID hostId, String topic, String password) {}

    @SendTo("/topic/chat/{chatId}/messages")
    @MessageMapping("/api/v1/chat/{chatId}/messages")
    ChatMessage send(@DestinationVariable UUID chatId, @RequestBody ChatMessage chatMessage) {
        chatService.sendMessage(chatId, chatMessage.userId(), chatMessage.content());

        return chatMessage;
    }

    private record ChatMessage(UUID userId, String content) {}
}
