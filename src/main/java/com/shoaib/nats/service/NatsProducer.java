package com.shoaib.nats.service;

import org.springframework.stereotype.Service;

import io.nats.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

@Service
public class NatsProducer {

    private final Connection natsConnection;

    @Autowired
    public NatsProducer(Connection natsConnection) {
        this.natsConnection = natsConnection;
    }

    public void sendMessage(String subject, String message) {
        try {
            natsConnection.publish(subject, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Message sent to subject '" + subject + "': " + message);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

