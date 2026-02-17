package net.chatstorage.repository;

import net.chatstorage.model.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatSessionRepo extends JpaRepository<ChatSession, Long> {
    List<ChatSession> findByUserId(String userId);

    @Query("SELECT c FROM ChatSession c WHERE c.userId=:userId AND c.markFavorite=true ORDER BY c.createdAt DESC")
    List<ChatSession> findFavoriteSessionsByUserId(String userId);

    @Modifying
    @Transactional
    @Query("UPDATE ChatSession c SET c.sessionName=:newName, c.updatedAt=CURRENT_TIMESTAMP WHERE c.id=:sessionId")
    int renameSession(Long sessionId, String newName);

    @Modifying
    @Transactional
    @Query("UPDATE ChatSession c SET c.markFavorite=:favorite, c.updatedAt=CURRENT_TIMESTAMP WHERE c.id=:sessionId")
    int updateFavoriteStatus(Long sessionId, boolean favorite);
}
