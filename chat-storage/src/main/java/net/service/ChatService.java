package net.chatstorage.service;

import net.chatstorage.record.ChatSessionRecord;
import net.chatstorage.record.MessageRecord;
import net.chatstorage.model.ChatSession;
import net.chatstorage.model.Message;
import net.chatstorage.repository.ChatSessionRepo;
import net.chatstorage.repository.MessageRepos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private static final Logger logger = LogManager.getLogger(ChatService.class);

    @Autowired
    private ChatSessionRepo chatSessionRepo;

    @Autowired
    private MessageRepo messageRepo;

    public ChatSessionRecord createSession(String userId, String sessionName) {
        logger.info("Creating chat session for user with userId: {}", userId);
        ChatSession chatSession = new ChatSession();
        chatSession.setUserId(userId);
        chatSession.setSessionName(sessionName);
        chatSession = chatSessionRepo.save(chatSession);
        return new ChatSessionRecord(
                chatSession.getId(),
                chatSession.getUserId(),
                chatSession.getSessionName(),
                chatSession.getMarkFavorite(),
                chatSession.getCreatedTs(),
                chatSession.getUpdatedTs()
        );
    }

    public List<MessageRecord> getMessages(Long sessionId) {
        logger.info("Getting messages for session: {}", sessionId);
        List<Message> messages = messageRepo.findBySessionIdOrderByCreatedTs(sessionId);
        return messages.stream().map(msg -> new MessageRecord(
                msg.getId(),
                msg.getSessionId(),
                msg.getSender(),
                msg.getMessageContent(),
                msg.getContext(),
                msg.getCreatedTs()
        )).toList();
    }

    public MessageRecord addMessage(Long sessionId, String sender, String messageContent, String context) {
        Message message = new Message();
        message.setSessionId(sessionId);
        message.setSender(sender);
        message.setMessageContent(messageContent);
        message.setContext(context);
        message = messageRepository.save(message);
        return new MessageRecord(
                message.getId(),
                message.getSessionId(),
                message.getSender(),
                message.getMessageContent(),
                message.getContext(),
                message.getCreatedTs()
        );
    }

    @Transactional
    public void renameSession(Long sessionId, String newName) {
        chatSessionRepo.renameSession(sessionId, newName);
    }

    @Transactional
    public void markFavorite(Long sessionId, boolean favorite) {
        chatSessionRepo.updateFavoriteStatus(sessionId, favorite);
    }

    public void deleteSession(Long sessionId) {
        chatSessionRepo.deleteById(sessionId);
    }
}
