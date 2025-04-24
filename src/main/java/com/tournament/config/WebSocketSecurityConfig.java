package com.tournament.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;
import org.springframework.messaging.Message;

@Configuration
@EnableWebSocketSecurity
public class WebSocketSecurityConfig {

    @Bean
    public AuthorizationManager<Message<?>> authorizationManager(MessageMatcherDelegatingAuthorizationManager.Builder messages) {
        messages
                .simpDestMatchers("/user/queue/errors").permitAll()
                .simpDestMatchers("/topic/public").permitAll()
                .simpSubscribeDestMatchers("/topic/private/**").authenticated()
                .simpDestMatchers("/app/**").authenticated()
                .simpDestMatchers("/admin/**").hasRole("ADMIN")
                .anyMessage().denyAll();

        return messages.build();
    }
}
