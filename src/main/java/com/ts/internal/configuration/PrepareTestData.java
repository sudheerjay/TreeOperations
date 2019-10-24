package com.ts.internal.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.ts.internal.services.NodeOperations;

@Configuration
@EnableAutoConfiguration
public class PrepareTestData extends SpringBootServletInitializer {
	
    public static void main(final String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PrepareTestData.class, args);
        context.getBean(NodeOperations.class).writeTree();
    }
}