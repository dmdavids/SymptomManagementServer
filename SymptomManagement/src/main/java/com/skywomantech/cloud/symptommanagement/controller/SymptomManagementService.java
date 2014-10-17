package com.skywomantech.cloud.symptommanagement.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skywomantech.cloud.symptommanagement.client.SymptomManagementApi;
import com.skywomantech.cloud.symptommanagement.repository.Medication;
import com.skywomantech.cloud.symptommanagement.repository.MedicationRepository;
import com.skywomantech.cloud.symptommanagement.repository.Patient;
import com.skywomantech.cloud.symptommanagement.repository.PatientRepository;
import com.skywomantech.cloud.symptommanagement.repository.Physician;
import com.skywomantech.cloud.symptommanagement.repository.PhysicianRepository;

@Controller
public class SymptomManagementService {

	static final Logger LOG = LoggerFactory.getLogger(SymptomManagementService.class);
	
	@Autowired
	private PatientRepository patients;
	
	@Autowired
	private PhysicianRepository physicians;
	
	@Autowired
	private MedicationRepository medications;

	@RequestMapping(value=SymptomManagementApi.PATIENT_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Patient> getPatientList() {
		return patients.findAll();
	}

	@RequestMapping(value=SymptomManagementApi.PATIENT_PATH+SymptomManagementApi.ID_PATH, method=RequestMethod.GET)
	public @ResponseBody Patient getPatient(@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		return patients.findOne(id);
	}

	@RequestMapping(value=SymptomManagementApi.PATIENT_PATH, method=RequestMethod.POST)
	public @ResponseBody Patient addPatient(@RequestBody Patient patient) {
		LOG.debug("Adding this patient : " + patient);
		return patients.save(patient);
	}

	@RequestMapping(value=SymptomManagementApi.PATIENT_PATH+SymptomManagementApi.ID_PATH, method=RequestMethod.PUT)
	public @ResponseBody Patient updatePatient(@PathVariable(SymptomManagementApi.ID_PARAMETER) String id, 
			@RequestBody Patient patient) {
		return patients.save(patient);
	}

	@RequestMapping(value=SymptomManagementApi.PATIENT_PATH+SymptomManagementApi.ID_PATH, method=RequestMethod.DELETE)
	public @ResponseBody Patient deletePatient(@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		Patient found = patients.findOne(id);
		if (found != null) {
			patients.delete(id);
		}
		return found;
	}

	@RequestMapping(value=SymptomManagementApi.PATIENT_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Patient> findByPatientName(@RequestParam(SymptomManagementApi.NAME_PARAMETER) String name) {
		return patients.findByName(name);
	}

	@RequestMapping(value=SymptomManagementApi.PHYSICIAN_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Physician> getAllPhysicians() {
		return physicians.findAll();
	}

	@RequestMapping(value=SymptomManagementApi.PHYSICIAN_PATH+SymptomManagementApi.ID_PATH, method=RequestMethod.GET)
	public @ResponseBody Physician getPhysician(@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		return physicians.findOne(id);
	}

	@RequestMapping(value=SymptomManagementApi.PHYSICIAN_PATH, method=RequestMethod.POST)
	public @ResponseBody Physician addPhysician(@RequestBody Physician physician) {
		return physicians.save(physician);
	}

	@RequestMapping(value=SymptomManagementApi.PHYSICIAN_PATH+SymptomManagementApi.ID_PATH, method=RequestMethod.PUT)
	public @ResponseBody Physician updatePhysician(@PathVariable(SymptomManagementApi.ID_PARAMETER) String id, 
			@RequestBody Physician physician) {
		return physicians.save(physician);
	}

	@RequestMapping(value=SymptomManagementApi.PHYSICIAN_PATH+SymptomManagementApi.ID_PATH, method=RequestMethod.DELETE)
	public @ResponseBody Physician deletePhysician(@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		Physician found = physicians.findOne(id);
		if (found != null) {
			physicians.delete(id);
		}
		return found;
	}

	@RequestMapping(value=SymptomManagementApi.PHYSICIAN_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Physician> findByPhysicianName(@RequestParam(SymptomManagementApi.NAME_PARAMETER) String name) {
		return physicians.findByName(name);
	}

	@RequestMapping(value=SymptomManagementApi.MEDICATION_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Medication> getAllMedications() {
		return medications.findAll();
	}
	
	@RequestMapping(value=SymptomManagementApi.MEDICATION_PATH+SymptomManagementApi.ID_PATH, method=RequestMethod.GET)
	public @ResponseBody Medication getMedication(@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		return medications.findOne(id);
	}

	@RequestMapping(value=SymptomManagementApi.MEDICATION_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Medication> findByMedicationName(@RequestParam(SymptomManagementApi.NAME_PARAMETER) String name) {
		return medications.findByName(name);
	}
	
	@RequestMapping(value=SymptomManagementApi.MEDICATION_PATH+SymptomManagementApi.ID_PATH, method=RequestMethod.DELETE)
	public @ResponseBody Medication deleteMedication(@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		Medication found = medications.findOne(id);
		if (found != null) {
			medications.delete(id);
		}
		return found;
	}

}
