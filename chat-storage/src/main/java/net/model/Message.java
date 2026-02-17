package net.chatstorage.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private ChatSession chatSession;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String messageContent;

    private String context;

    private LocalDateTime createdTs = LocalDateTime.now();

    // getters & setters
}
