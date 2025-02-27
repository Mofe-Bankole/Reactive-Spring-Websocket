package com.devmofe.Reactive.Spring.Websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@Component
@Configuration
public class NeonWebSocketConfig implements WebFluxConfigurer {

    private static final Logger LOGGER =
            Logger.getLogger(NeonWebSocketConfig.class.getName());

    @Bean
    public HandlerMapping webSocketMapping(NeonSocketHandler neonSocketHandler){
        Map<String , WebSocketHandler> neonSocketHandlerMap = new HashMap<>();
        neonSocketHandlerMap.put("/event-emitter" , neonSocketHandler);

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(neonSocketHandlerMap);
        mapping.setOrder(1);

        return mapping;
    }


    @Bean
    public WebSocketHandlerAdapter socketHandlerAdapter(){
        return new WebSocketHandlerAdapter(webSocketService());
    }

    @Bean
    private WebSocketService webSocketService(){
        return new HandshakeWebSocketService();
    }
    @Bean
    public NeonSocketHandler neonSocketHandler(){
        return new NeonSocketHandler();
    }
}
