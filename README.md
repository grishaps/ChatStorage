# ChatStorage



--Chat storage (Java , Springboot, Microservices , log4j2 )



Min REST APIs to implement


GET /sessions/{session_id}/messages: get messages for each session id.
POST /sessions: Create a new session.
POST /sessions/{session_id}/messages: Create a new message to a session id.
PUT /sessions/{session_id}/rename: To rename a chat session.
PUT /sessions/{session_id}/favorite: Mark/unmark a session as favorite.
DELETE /sessions/{session_id}: To delete a session



DB design - PostgreSql

msg table

id (P Key)
session_id (F Key to other table)
message_content (Text)
sender_info (Enum: User, AI)
context (text or json format)
created_ts (Timestamp)

ChatSession table

id (P Key)
user_id (F Key )
session_name (String)
mark_favorite (Boolean)
created_ts (Timestamp)
updated_ts (Timestamp)


DB table creation

CREATE TABLE chat_sessions (
    id BIGSERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    session_name VARCHAR(255) NOT NULL,
    mark_favorite BOOLEAN DEFAULT FALSE,
    created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE messages (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL,
    sender VARCHAR(50) NOT NULL,
    message_content TEXT NOT NULL,
    context TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_session
        FOREIGN KEY(session_id)
        REFERENCES chat_sessions(id)
        ON DELETE CASCADE
);

For scalability -

CREATE INDEX idx_chat_sessions_user_id ON chat_sessions(user_id);
CREATE INDEX idx_chat_sessions_favorite ON chat_sessions(is_favorite);
CREATE INDEX idx_messages_session_id ON messages(session_id);
CREATE INDEX idx_messages_created_at ON messages(created_at);


requirement - •	All APIs should be protected using API key authentication (read from environment variables).
Solution- can use Spring Security for managing API authentication by storing it in  env variables

requirement - •	Implement rate limiting to prevent abuse of the APIs.
solution- Spring RateLimiter lib

log4j uses here for logging
Never logs API keys ,Passwords ,Tokens and Sensitive user content




