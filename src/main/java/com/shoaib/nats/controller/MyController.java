package com.shoaib.nats.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoaib.nats.service.NatsProducer;

@RestController
public class MyController {
	
	@Autowired
	 private  NatsProducer producer;

	  

	    @GetMapping("/publish")
	    public String publishMessage() throws JsonProcessingException {
	    	 String subject="test-shoaib";
	    	 String message = "i am sending message to kafka";
	    	 List<Map<String,Object>> lst = new ArrayList<>();
	    	 
	    	 for (int i = 0; i < 10; i++) {
	             Map<String, Object> data = new HashMap<>();
	             data.put("name", getName(i));                 
	             data.put("city", getCity(i));                 
	             data.put("id", 1000 + i);                      // Realistic ID
	             data.put("salary", 55000 + (i * 2000));        // Example salaries
	             data.put("locations", getLocation(i));         // Example locations

	             lst.add(data);
	         }
	    	 
	    	 String dataToBeSended = new ObjectMapper().writeValueAsString(lst);
	        producer.sendMessage(subject, dataToBeSended);
	        return "Message published!";
	    }
	
	@GetMapping("/test")
	public String test() {
		return "ok";
	}
	
	 private static String getName(int index) {
	        String[] names = {
	            "John Smith", "Alice Johnson", "Michael Brown", "Emily Davis", "James Wilson",
	            "Sophia Moore", "David Taylor", "Olivia Anderson", "Daniel Martinez", "Isabella Lee"
	        };
	        return names[index];
	    }

	    private static String getCity(int index) {
	        switch (index) {
	            case 0: return "New York";
	            case 1: return "Los Angeles";
	            case 2: return "Chicago";
	            case 3: return "Houston";
	            case 4: return "Phoenix";
	            case 5: return "Philadelphia";
	            case 6: return "San Antonio";
	            case 7: return "San Diego";
	            case 8: return "Dallas";
	            case 9: return "San Jose";
	            default: return "Unknown";
	        }
	    }

	    private static String getLocation(int index) {
	        switch (index) {
	            case 0: return "Manhattan";
	            case 1: return "Beverly Hills";
	            case 2: return "Lakeview";
	            case 3: return "Downtown Houston";
	            case 4: return "Tempe";
	            case 5: return "University City";
	            case 6: return "Stone Oak";
	            case 7: return "La Jolla";
	            case 8: return "Highland Park";
	            case 9: return "Willow Glen";
	            default: return "Unknown";
	        }
	    }

}
