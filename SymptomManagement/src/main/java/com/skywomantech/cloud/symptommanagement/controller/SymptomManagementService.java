package com.skywomantech.cloud.symptommanagement.controller;

import java.math.BigInteger;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class SymptomManagementService implements SymptomManagementApi {

	static final Logger LOG = LoggerFactory.getLogger(SymptomManagementService.class);
	
	@Autowired
	private PatientRepository patients;
	
	@Autowired
	private PhysicianRepository physicians;
	
	@Autowired
	private MedicationRepository medications;

	@RequestMapping(value=PATIENT_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Patient> getAllPatients() {
		LOG.info("Finding all patients");
		return patients.findAll();
	}

	@RequestMapping(value=PATIENT_PATH+ID_PATH, method=RequestMethod.GET)
	public @ResponseBody Patient getPatient(@RequestParam(ID_PARAMETER) long userId) {
		return patients.findOne(userId);
	}

	@RequestMapping(value=PATIENT_PATH, method=RequestMethod.POST)
	public @ResponseBody Patient addPatient(@RequestBody Patient patient) {
		LOG.info("Adding this patient : " + patient);
		return patients.save(patient);
	}

	@RequestMapping(value=PATIENT_PATH+ID_PATH, method=RequestMethod.PUT)
	public @ResponseBody Patient updatePatient(@RequestParam(ID_PARAMETER) long userId, 
			@RequestBody Patient patient) {
		patient.setId(BigInteger.valueOf(userId));
		LOG.info("Updating this patient: " + patient);
		return patients.save(patient);
	}

	@RequestMapping(value=PATIENT_PATH+ID_PATH, method=RequestMethod.DELETE)
	public @ResponseBody void deletePatient(@RequestParam(ID_PARAMETER) long userId) {
		patients.delete(userId);
	}

	@RequestMapping(value=PATIENT_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Patient> findByPatientName(@RequestParam(NAME_PARAMETER) String name) {
		return patients.findByName(name);
	}

	@RequestMapping(value=PHYSICIAN_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Physician> getAllPhysicians() {
		return physicians.findAll();
	}

	@RequestMapping(value=PHYSICIAN_PATH+ID_PATH, method=RequestMethod.GET)
	public @ResponseBody Physician getPhysician(@RequestParam(ID_PARAMETER) long userId) {
		return physicians.findOne(userId);
	}

	@RequestMapping(value=PHYSICIAN_PATH, method=RequestMethod.POST)
	public @ResponseBody Physician addPhysician(@RequestBody Physician physician) {
		return physicians.save(physician);
	}

	@RequestMapping(value=PHYSICIAN_PATH+ID_PATH, method=RequestMethod.PUT)
	public @ResponseBody Physician updatePhysician(@RequestParam(ID_PARAMETER) long userId, 
			@RequestBody Physician physician) {
		physician.setId(BigInteger.valueOf(userId));
		LOG.info("Updating this physician: " + physician);
		return physicians.save(physician);
	}

	@RequestMapping(value=PHYSICIAN_PATH+ID_PATH, method=RequestMethod.DELETE)
	public @ResponseBody void deletePhysician(@RequestParam(ID_PARAMETER) long userId) {
		physicians.delete(userId);
	}

	@RequestMapping(value=PHYSICIAN_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Physician> findByPhysicianName(@RequestParam(NAME_PARAMETER) String name) {
		return physicians.findByName(name);
	}

	@RequestMapping(value=MEDICATION_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Medication> getAllMedications() {
		return medications.findAll();
	}

	@RequestMapping(value=MEDICATION_PATH+ID_PATH, method=RequestMethod.GET)
	public @ResponseBody Medication getMedication(@RequestParam(ID_PARAMETER) long medId) {
		return medications.findOne(medId);
	}

	@RequestMapping(value=MEDICATION_PATH, method=RequestMethod.POST)
	public @ResponseBody Medication addMedication(@RequestBody Medication medication) {
		LOG.info("Adding this medication : " + medication);
		return medications.save(medication);
	}

	@RequestMapping(value=MEDICATION_PATH+ID_PATH, method=RequestMethod.PUT)
	public @ResponseBody Medication updateMedication(long medId, @RequestBody Medication medication) {
		medication.setId(BigInteger.valueOf(medId));
		return medications.save(medication);
	}

	@RequestMapping(value=MEDICATION_PATH+ID_PATH, method=RequestMethod.DELETE)
	public @ResponseBody void deleteMedication(@RequestParam(ID_PARAMETER) long medId) {
		medications.delete(medId);
	}

	@RequestMapping(value=MEDICATION_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Medication> findByMedicationName(@RequestParam(NAME_PARAMETER) String name) {
		return medications.findByName(name);
	}
}
