package com.skywomantech.cloud.symptommanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skywomantech.cloud.symptommanagement.json.ResourcesMapper;

@EnableAutoConfiguration
@EnableMongoRepositories
@EnableWebMvc
@Configuration
@ComponentScan
public class Application extends RepositoryRestMvcConfiguration {
	
	// Tell Spring to launch our app!
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Like this formatter better
	 */
	@Override
	public ObjectMapper halObjectMapper(){
		return new ResourcesMapper();
	}
}
