package io.shirohoo.example.chat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    @Query("SELECT cm FROM ChatMessage cm WHERE cm.id = :chatId ORDER BY cm.createdAt")
    Set<ChatMessage> findByChat(int chatId);
}
