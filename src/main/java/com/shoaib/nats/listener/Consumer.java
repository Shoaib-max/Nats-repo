package com.shoaib.nats.listener;

import org.springframework.stereotype.Component;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Consumer {

    private final Connection natsConnection;

    @Autowired
    public Consumer(Connection natsConnection) {
        this.natsConnection = natsConnection;
        setupListener();
    }

    private void setupListener() {
        try {
            Dispatcher dispatcher = natsConnection.createDispatcher((msg) -> {
                String message = new String(msg.getData(), StandardCharsets.UTF_8);
//                System.out.println("Received message on subject '" + msg.getSubject() + "': " + message);
                try {
					List<Map<String,Object>> lst = new ObjectMapper().readValue(message, List.class);
					for(Map<String,Object> m : lst) {
						for(Map.Entry<String, Object> ms : m.entrySet()) {
							System.out.println(ms.getKey() + " : " + ms.getValue());
						}
					}
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });

            dispatcher.subscribe("test-shoaib");
            System.out.println("Consumer subscribed to subject 'subject-name'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

