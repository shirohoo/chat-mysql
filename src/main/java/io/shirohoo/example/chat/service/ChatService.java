package io.shirohoo.example.chat.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import io.shirohoo.example.chat.domain.Chat;
import io.shirohoo.example.chat.domain.ChatMessage;
import io.shirohoo.example.chat.domain.ChatMessageRepository;
import io.shirohoo.example.chat.domain.ChatRepository;
import io.shirohoo.example.chat.domain.ChatUsers;
import io.shirohoo.example.chat.domain.ChatUsersRepository;
import io.shirohoo.example.chat.domain.User;
import io.shirohoo.example.chat.domain.UserRepository;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatUsersRepository chatUsersRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public Set<Chat> getChats() {
        return new HashSet<>(chatRepository.findAll()); // TODO: to pagination
    }

    @Transactional
    public Chat createChat(UUID hostId, String topic, String password) {
        if (topic == null || topic.isBlank()) {
            throw new UnsupportedOperationException("topic is blank !");
        }

        // if public chat
        if (password == null || password.isBlank()) {
            password = null;
        }

        User host = userRepository.findById(hostId).orElseGet(() -> userRepository.save(new User(hostId)));
        Chat chat = chatRepository.save(new Chat(host, topic, password));
        ChatUsers joinedHost = chat.join(host);
        chatUsersRepository.save(joinedHost);
        return chat;
    }

    @Transactional
    public ChatUsers joinChat(UUID chatId, UUID userId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow();
        User user = userRepository.findById(userId).orElseGet(() -> userRepository.save(new User(userId)));

        return chatUsersRepository.findBy(chat, user).orElseGet(() -> {
            ChatUsers participant = chat.join(user);
            return chatUsersRepository.save(participant);
        });
    }

    @Transactional(readOnly = true)
    public Set<ChatMessage> getMessages(int chatId) {
        return chatMessageRepository.findByChat(chatId); // TODO: to pagination
    }

    @Transactional
    public void sendMessage(UUID chatId, UUID userId, String content) {
        if (content == null || content.isBlank()) {
            return;
        }

        ChatUsers participant = chatUsersRepository
                .findBy(chatRepository.getReferenceById(chatId), userRepository.getReferenceById(userId))
                .orElseThrow();
        ChatMessage chatMessage = new ChatMessage(participant, content);
        chatMessageRepository.save(chatMessage);
    }
}
