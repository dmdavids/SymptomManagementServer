package com.skywomantech.cloud.symptommanagement.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skywomantech.cloud.symptommanagement.client.SymptomManagementApi;

@RepositoryRestResource(path = "/credential")
public interface UserCredentialRepository extends MongoRepository<UserCredential, String>{

		// Find all credentials with a matching name
		public Collection<UserCredential> findByUserName(@Param(SymptomManagementApi.NAME_PARAMETER) String userName);
		
}
