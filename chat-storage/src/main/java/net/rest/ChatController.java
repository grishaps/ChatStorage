package net.chatstorage.rest;

import net.chatstorage.record.ChatSessionRecord;
import net.chatstorage.record.MessageRecord;
import net.chatstorage.service.ChatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class ChatController {

    private static final Logger logger = LogManager.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ChatSessionRecord createSession(@RequestBody ChatSessionRecord chatSessionRecord) {
        logger.info("Request to create session: {}", chatSessionRecord);
        return chatService.createSession(chatSessionRecord.userId(), chatSessionRecord.sessionName());
    }

    @GetMapping("/{sessionId}/messages")
    public List<MessageRecord> getMessages(@PathVariable Long sessionId) {
        return chatService.getMessages(sessionId);
    }

    @PostMapping("/{sessionId}/messages")
    public MessageRecord addMessage(@PathVariable Long sessionId, @RequestBody MessageRecord messageRecord) {
        return chatService.addMessage(sessionId, messageRecord.sender(), messageRecord.messageContent(), messageRecord.context());
    }

    @PutMapping("/session/{id}/rename")
    public void renameSession(@PathVariable Long id, @RequestParam String newName) {
        chatService.renameSession(id, newName);
    }

    @PutMapping("/session/{id}/favorite")
    public void markFavorite(@PathVariable Long id, @RequestParam boolean favorite) {
        chatService.markFavorite(id, favorite);
    }

    @DeleteMapping("/{sessionId}")
    public void deleteSession(@PathVariable Long sessionId) {
        chatService.deleteSession(sessionId);
    }
}

