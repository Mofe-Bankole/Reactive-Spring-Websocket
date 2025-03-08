package com.devmofe.Reactive.Spring.Websocket.Handlers;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class NexusSocketHandler implements WebSocketHandler {
    private static final org.slf4j.Logger LOGGER =
            LoggerFactory.getLogger(NexusSocketHandler.class.getName());

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(
                session.receive()
                        .map(msg -> {
                            String receivedMsg = msg.getPayloadAsText();
                            return session.textMessage("Echo : " + receivedMsg);
                        })
        );
    }
}
