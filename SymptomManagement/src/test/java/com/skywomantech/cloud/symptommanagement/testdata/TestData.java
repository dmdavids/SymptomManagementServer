package com.skywomantech.cloud.symptommanagement.testdata;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skywomantech.cloud.symptommanagement.repository.Medication;
import com.skywomantech.cloud.symptommanagement.repository.Patient;
import com.skywomantech.cloud.symptommanagement.repository.Physician;


/**
 * This is a utility class to aid in the construction of
 * Video objects with random names, urls, and durations.
 * The class also provides a facility to convert objects
 * into JSON using Jackson, which is the format that the
 * VideoSvc controller is going to expect data in for
 * integration testing.
 * 
 * @author jules
 *
 */
public class TestData {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Construct and return a Patient object with a
	 * random name and password
	 * 
	 * @return
	 */
	public static Patient randomPatient() {
		String name = "Donald Duck";
		//Patient p = null;
		return new Patient(name);
	}
	
	
	public static Physician randomPhysician() {
		String name = "Minnie Mouse";
		String patientName = "Daisy";
		Set<Patient> patients = new HashSet<Patient>();
		patients.add(new Patient(patientName));
		Physician p = new Physician(name, patients );
		return p;
	}
	
	
	public static Medication randomMedication() {
		String name = "Dancing Feet";
		Medication m = new Medication(name);
		return m;
	}
	
	/**
	 *  Convert an object to JSON using Jackson's ObjectMapper
	 *  
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String toJson(Object o) throws Exception{
		return objectMapper.writeValueAsString(o);
	}
}
