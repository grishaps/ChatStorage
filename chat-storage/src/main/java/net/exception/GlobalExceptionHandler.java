package net.chatstorage.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {

        logger.error("Unhandled exception occurred", ex);

        return ResponseEntity
                .status(500)
                .body("Internal Server Error");
    }
}
