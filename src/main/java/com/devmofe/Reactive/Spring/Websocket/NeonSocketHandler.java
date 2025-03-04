package com.devmofe.Reactive.Spring.Websocket;

import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class NeonSocketHandler implements WebSocketHandler {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NeonSocketHandler.class.getName());

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send( // Sends message back to the Client
                session.receive() // Listens from incoming messages from the client
                        .map(msg -> { // Transforms the message
                            String receivedMsg = msg.getPayloadAsText(); // Extracts the message as text
                            LOGGER.info("Received Message: " + receivedMsg); // Logs the message
                            return session.textMessage("Echo : " + receivedMsg); // Echoes back the message
                        })
        );
    }
}
