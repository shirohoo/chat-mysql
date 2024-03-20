package io.shirohoo.example.chat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, length = 36)
    private UUID id;

    public User(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{id='%s'}".formatted(id);
    }
}
