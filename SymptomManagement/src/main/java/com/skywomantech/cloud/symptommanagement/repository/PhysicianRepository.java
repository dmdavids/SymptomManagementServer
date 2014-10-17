package com.skywomantech.cloud.symptommanagement.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skywomantech.cloud.symptommanagement.client.SymptomManagementApi;

@RepositoryRestResource(path = SymptomManagementApi.PHYSICIAN_PATH)
public interface PhysicianRepository extends MongoRepository<Physician, String> {

	// Find all patients with a similar name
    public Collection<Physician> findByName(
    		@Param(SymptomManagementApi.NAME_PARAMETER) String name);
}
