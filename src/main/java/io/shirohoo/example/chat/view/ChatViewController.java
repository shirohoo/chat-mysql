package io.shirohoo.example.chat.view;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.UUID;

import io.shirohoo.example.chat.service.ChatService;

@Controller
@RequiredArgsConstructor
class ChatViewController {
    private final ChatService chatService;

    @GetMapping("/")
    ModelAndView index() {
        var chats = chatService.getChats();

        return new ModelAndView("index", Map.of("chats", chats));
    }

    @GetMapping("/chat")
    ModelAndView joinChat(String chatId, String userId) {
        var chatUsers = chatService.joinChat(UUID.fromString(chatId), UUID.fromString(userId));
        var chatMessages = chatService.getMessages(chatUsers.getId());

        return new ModelAndView(
                "chat",
                Map.of(
                        "chatUsers", chatUsers,
                        "chatMessages", chatMessages));
    }
}
