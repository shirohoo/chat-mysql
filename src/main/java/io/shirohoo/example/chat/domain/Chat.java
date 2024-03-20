package io.shirohoo.example.chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "chat_id", nullable = false, length = 36)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "host_id", nullable = false, columnDefinition = "VARCHAR(36)")
    private User host;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Chat(User host, String topic, String password) {
        this.host = host;
        this.topic = topic;
        this.password = password;
    }

    public ChatUsers join(User user) {
        return new ChatUsers(this, user);
    }

    @Override
    public String toString() {
        return "Chat{id='%s', host='%s', topic='%s', createdAt='%s'}".formatted(id, host, topic, createdAt);
    }
}
