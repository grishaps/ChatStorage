package net.chatstorage.record;

import java.time.LocalDateTime;

public record MessageRecord(
        Long id,
        Long sessionId,
        String sender,
        String messageContent,
        String context,
        LocalDateTime createdTs
) {}
