package net.chatstorage.repository;

import net.chatstorage.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByChatSessionIdOrderByCreatedTs(Long sessionId);

    @Query("SELECT m FROM Message m WHERE m.sender=:sender ORDER BY m.createdTs")
    List<Message> findMessagesBySenderOrdered(String sender);

    @Query("SELECT m FROM Message m WHERE m.createdTs BETWEEN :start AND :end ORDER BY m.createdTs")
    List<Message> findMessagesWithinTimeRange(LocalDateTime start, LocalDateTime end);

    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.messageContent=:newContent WHERE m.id=:messageId")
    int updateMessageContent(Long messageId, String newContent);

    @Modifying
    @Transactional
    @Query("DELETE FROM Message m WHERE m.chatSession.id=:sessionId")
    int deleteMessagesBySessionId(Long sessionId);

    @Query("SELECT COUNT(m) FROM Message m WHERE m.chatSession.id=:sessionId")
    Long countMessagesBySessionId(Long sessionId);
}

