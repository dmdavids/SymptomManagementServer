package com.skywomantech.cloud.symptommanagement.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

import com.skywomantech.cloud.symptommanagement.client.SymptomManagementApi;

@RepositoryRestResource(path = SymptomManagementApi.PATIENT_PATH)
public interface PatientRepository extends MongoRepository<Patient, String>{
	
	// Find all patients with a similar name
    public Collection<Patient> findByLastName(@Param(SymptomManagementApi.NAME_PARAMETER) String lastName);

}
