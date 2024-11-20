package com.shoaib.nats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.nats.client.Connection;
import io.nats.client.Nats;

@Configuration
public class NatsConfig {
	
	   @Bean
	    public Connection natsConnection() throws Exception {
	        return Nats.connect("13.200.242.88:4222");
	    }

}
