package com.four.proxyannotation.conf;

import com.four.proxyannotation.HandlerDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguration {

    @Bean
    public HandlerDispatcher handlerDispatcher () {
        return new HandlerDispatcher();
    }
}
