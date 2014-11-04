package com.skywomantech.cloud.symptommanagement;

import org.skywomantech.cloud.symptommanagement.auth.OAuth2SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableMongoRepositories
@EnableWebMvc
@Configuration
@ComponentScan
@Import(OAuth2SecurityConfiguration.class)
public class Application extends RepositoryRestMvcConfiguration {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
