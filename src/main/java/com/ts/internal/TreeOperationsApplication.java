package com.ts.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.ts.internal.services.NodeOperations;

@SpringBootApplication(exclude = { RedisRepositoriesAutoConfiguration.class })
public class TreeOperationsApplication {

	@Autowired
	NodeOperations nodeOperations;
	
	public static void main(String[] args) {
		SpringApplication.run(TreeOperationsApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void prepareTreeTestData() {
        nodeOperations.writeTree();    
	}

}
