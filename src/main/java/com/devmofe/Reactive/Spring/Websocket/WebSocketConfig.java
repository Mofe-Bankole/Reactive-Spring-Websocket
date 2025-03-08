package com.devmofe.Reactive.Spring.Websocket;

import com.devmofe.Reactive.Spring.Websocket.Handlers.NeonSocketHandler;
import com.devmofe.Reactive.Spring.Websocket.Handlers.NexusSocketHandler;
import org.slf4j.LoggerFactory;
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


@Component
@Configuration
public class WebSocketConfig implements WebFluxConfigurer {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WebSocketConfig.class.getName());

    @Bean
    public HandlerMapping webSocketMapping(NeonSocketHandler neonSocketHandler , NexusSocketHandler nexusSocketHandler){
        Map<String , WebSocketHandler> neonSocketHandlerMap = new HashMap<>();
        neonSocketHandlerMap.put("/neon" , neonSocketHandler);

        Map<String , WebSocketHandler> nexusSocketHandlerMap = new HashMap<>();
        nexusSocketHandlerMap.put("/nexus" , nexusSocketHandler);

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(neonSocketHandlerMap);
        mapping.setUrlMap(nexusSocketHandlerMap);

        mapping.setOrder(-1);
        return mapping;
    }


    @Bean
    public WebSocketHandlerAdapter socketHandlerAdapter(){
        return new WebSocketHandlerAdapter(webSocketService());
    }

    private WebSocketService webSocketService(){
        return new HandshakeWebSocketService();
    }

    @Bean
    public NeonSocketHandler neonSocketHandler(){
        return new NeonSocketHandler();
    }

    @Bean
    public NexusSocketHandler nexusSocketHandler(){
        return new NexusSocketHandler();
    }
}