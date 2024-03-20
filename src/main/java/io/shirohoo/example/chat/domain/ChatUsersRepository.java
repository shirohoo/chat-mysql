package io.shirohoo.example.chat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatUsersRepository extends JpaRepository<ChatUsers, Integer> {
    @Query("SELECT cu FROM ChatUsers cu WHERE cu.chat = :chat AND cu.user = :user")
    Optional<ChatUsers> findBy(Chat chat, User user);
}
