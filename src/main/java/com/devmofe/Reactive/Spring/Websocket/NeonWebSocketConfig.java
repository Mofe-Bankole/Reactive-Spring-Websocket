import com.devmofe.Reactive.Spring.Websocket.NeonSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@Component
@Configuration
public class NeonWebSocketConfig implements WebFluxConfigurer {

    private static final Logger LOGGER =
            Logger.getLogger(NeonWebSocketConfig.class.getName());

    @Bean
    public HandlerMapping webSocketMapping(){
        Map<String  , NeonSocketHandler> neonSocketHandlerMap = new HashMap<>();
        neonSocketHandlerMap.put("/event-emitter" , new NeonSocketHandler());

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(neonSocketHandlerMap);
        mapping.setOrder(1);
        return mapping;
    }

    //ASH protocol
    @Bean
    public WebSocketHandlerAdapter socketHandlerAdapter(){
        return new WebSocketHandlerAdapter(webSocketService());
    }

    @Bean
    private WebSocketService webSocketService(){
        return new HandshakeWebSocketService();
    }
    public NeonSocketHandler neonSocketHandler(){
        return new NeonSocketHandler();
    }
}
