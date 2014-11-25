package com.skywomantech.cloud.symptommanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.skywomantech.cloud.symptommanagement.auth.OAuth2SecurityConfiguration;

@EnableAutoConfiguration
@EnableMongoRepositories
@EnableWebMvc
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan(basePackages = { "com.skywomantech.cloud.symptommanagement" })
@Import(OAuth2SecurityConfiguration.class)
public class Application extends RepositoryRestMvcConfiguration {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
