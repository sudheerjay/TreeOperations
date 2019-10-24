package com.ts.internal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.ts.internal.configuration.PrepareTestData;
import com.ts.internal.services.NodeOperations;

@SpringBootApplication
public class TreeOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreeOperationsApplication.class, args);
	}

}
