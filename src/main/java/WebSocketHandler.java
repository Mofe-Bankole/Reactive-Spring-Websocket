import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

public class WebSocketHandler implements org.springframework.web.reactive.socket.WebSocketHandler {
    private final Logger logger;
            
    public WebSocketHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(message -> {
                    logger.info("Received Message " + message);
                    session.send(Mono.just
                            (session.textMessage("Echo " + message))).subscribe();
                })
                .then();
    }
}
