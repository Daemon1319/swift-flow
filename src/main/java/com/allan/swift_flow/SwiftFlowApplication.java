package com.allan.swift_flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableCaching
public class SwiftFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiftFlowApplication.class, args);
	}

}
