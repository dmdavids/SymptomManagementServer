package com.skywomantech.cloud.symptommanagement.controller;

import java.util.Collection;

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

	@Autowired
	private PatientRepository patients;
	
	@Autowired
	private PhysicianRepository physicians;
	
	@Autowired
	private MedicationRepository medications;

	@RequestMapping(value=PATIENT_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Patient> getAllPatients() {
		return patients.findAll();
	}

	@RequestMapping(value=PATIENT_PATH+ID_PATH, method=RequestMethod.GET)
	public @ResponseBody Patient getPatient(@RequestParam(ID_PARAMETER) String userId) {
		// TODO Auto-generated method stub
		return new Patient();
	}

	@RequestMapping(value=PATIENT_PATH, method=RequestMethod.POST)
	public @ResponseBody Patient addPatient(@RequestBody Patient patient) {
		// TODO Auto-generated method stub
		return patients.save(patient);
	}


	@RequestMapping(value=PATIENT_PATH+ID_PATH, method=RequestMethod.PUT)
	public @ResponseBody Patient updatePatient(@RequestParam(ID_PARAMETER) String userId, 
			@RequestBody Patient patient) {
		// TODO Auto-generated method stub
		return new Patient();
	}

	@RequestMapping(value=PATIENT_PATH+ID_PATH, method=RequestMethod.DELETE)
	public @ResponseBody void deletePatient(@RequestParam(ID_PARAMETER) String userId) {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value=PATIENT_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Patient> findByPatientName(@RequestParam(NAME_PARAMETER) String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value=PHYSICIAN_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Physician> getAllPhysicians() {
		// TODO Auto-generated method stub
		return physicians.findAll();
	}

	@RequestMapping(value=PHYSICIAN_PATH+ID_PATH, method=RequestMethod.GET)
	public @ResponseBody Physician getPhysician(@RequestParam(ID_PARAMETER) String userId) {
		// TODO Auto-generated method stub
		return new Physician();
	}

	@RequestMapping(value=PHYSICIAN_PATH, method=RequestMethod.POST)
	public @ResponseBody Physician addPhysician(@RequestBody Physician physician) {
		return physicians.save(physician);
	}

	@RequestMapping(value=PHYSICIAN_PATH+ID_PATH, method=RequestMethod.PUT)
	public @ResponseBody Physician updatePhysician(@RequestParam(ID_PARAMETER) String userId, 
			@RequestBody Physician physician) {
		// TODO Auto-generated method stub
		return new Physician();
	}

	@RequestMapping(value=PHYSICIAN_PATH+ID_PATH, method=RequestMethod.DELETE)
	public @ResponseBody void deletePhysician(@RequestParam(ID_PARAMETER) String userId) {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value=PHYSICIAN_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Physician> findByPhysicianName(@RequestParam(NAME_PARAMETER) String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value=MEDICATION_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Medication> getAllMedications() {
		// TODO Auto-generated method stub
		return medications.findAll();
	}

	@RequestMapping(value=MEDICATION_PATH+ID_PATH, method=RequestMethod.GET)
	public @ResponseBody Medication getMedication(@RequestParam(ID_PARAMETER) String medId) {
		// TODO Auto-generated method stub
		return new Medication();
	}

	@RequestMapping(value=MEDICATION_PATH, method=RequestMethod.POST)
	public @ResponseBody Medication addMedication(@RequestBody Medication medication) {
		return medications.save(medication);
	}

	@RequestMapping(value=MEDICATION_PATH+ID_PATH, method=RequestMethod.PUT)
	public @ResponseBody Medication updateMedication(String medId, @RequestBody Medication medication) {
		// TODO Auto-generated method stub
		return new Medication();
	}

	@RequestMapping(value=MEDICATION_PATH+ID_PATH, method=RequestMethod.DELETE)
	public @ResponseBody void deleteMedication(@RequestParam(ID_PARAMETER) String medId) {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value=MEDICATION_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Medication> findByMedicationName(@RequestParam(NAME_PARAMETER) String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
