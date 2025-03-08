package com.devmofe.Reactive.Spring.Websocket;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.TomcatWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;

public class ReactiveClientWebsocket {
    public static void main(String[] args) throws Exception {

        String websocketURI = "ws://localhost:5822/";
        String message = "event-spring-reactive-client-websocket";

        WebSocketClient neonWebsocketClient = new ReactorNettyWebSocketClient();

        neonWebsocketClient.execute(
                URI.create(websocketURI),
                        session -> session.send(Mono.just(session.textMessage(message)))
                                .thenMany(session.receive()
                                        .map(WebSocketMessage::getPayloadAsText))
                                .then())
                .block();
    }
}
