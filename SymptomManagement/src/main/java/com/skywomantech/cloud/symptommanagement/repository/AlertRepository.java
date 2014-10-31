package com.skywomantech.cloud.symptommanagement.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

import com.skywomantech.cloud.symptommanagement.client.SymptomManagementApi;

@RepositoryRestResource(path = SymptomManagementApi.ALERT_PATH)
public interface AlertRepository extends MongoRepository<Alert, String>{
		
	// Find all notifications for specified physician
	public Collection<Alert> findByPhysicianId(@PathVariable(SymptomManagementApi.ID_PARAMETER) String id);

}
