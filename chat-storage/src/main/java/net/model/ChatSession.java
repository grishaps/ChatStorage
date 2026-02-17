package net.chatstorage.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_sessions")
public class ChatSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String sessionName;

    private Boolean markFavorite = false;

    private LocalDateTime createdTs = LocalDateTime.now();
    private LocalDateTime updatedTs = LocalDateTime.now();

    // getters & setters
}
