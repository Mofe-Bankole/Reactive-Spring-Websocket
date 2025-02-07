import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Collections;
import java.util.logging.Logger;


@Component
@Configuration
public class WebSocketConfig {

    @Bean
    public Logger logger() {
        return Logger.getLogger
                (WebSocketConfig.class.getName());
    }

    @Bean
    public HandlerMapping webSocketMapping(WebSocketHandler webSocketHandler){
        return new SimpleUrlHandlerMapping
                (Collections.singletonMap("/websocket" , webSocketHandler) , 1);
    }

    @Bean
    public WebSocketHandlerAdapter socketHandlerAdapter(){
        return new WebSocketHandlerAdapter(webSocketService());
    }

    private WebSocketService webSocketService() {
        return new HandshakeWebSocketService();
    }

    @Bean
    public WebSocketHandler webSocketHandler(Logger logger){
        return new WebSocketHandler(logger);
    }
}
