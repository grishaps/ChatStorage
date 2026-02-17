package net.chatstorage.record;

import java.time.LocalDateTime;

public record ChatSessionRecord(
        Long id,
        String userId,
        String sessionName,
        Boolean markFavorite,
        LocalDateTime createdTs,
        LocalDateTime updatedTs
) {}
