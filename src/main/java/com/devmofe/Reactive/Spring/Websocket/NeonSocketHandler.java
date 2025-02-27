package com.devmofe.Reactive.Spring.Websocket;

import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class NeonSocketHandler implements WebSocketHandler {
    private static final org.slf4j.Logger LOGGER =
            LoggerFactory.getLogger(NeonSocketHandler.class.getName());

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(
                session.receive()
                        .map(msg -> {
                            String receivedMsg = msg.getPayloadAsText();
                            LOGGER.info("Received Message: " + receivedMsg);
                            return session.textMessage("Echo: " + receivedMsg);
                        })
        );
    }
}
