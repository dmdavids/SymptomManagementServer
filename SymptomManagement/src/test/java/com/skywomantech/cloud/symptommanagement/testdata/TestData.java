package com.skywomantech.cloud.symptommanagement.testdata;

import java.util.UUID;

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
		// Construct a random identifier using Java's UUID class
		long id =  Long.parseLong(UUID.randomUUID().toString());
		String name = "Donald Duck";
		String password = "dummy";
		return new Patient(id,name, password, 0L);
	}
	
	
	public static Physician randomPhysician() {
		// Information about the video
		// Construct a random identifier using Java's UUID class
		long id =  Long.parseLong(UUID.randomUUID().toString());
		String name = "Minnie Mouse";
		String password = "mickey";
		return new Physician(id, name, password, 0L);
	}
	
	
	public static Medication randomMedication() {
		// Information about the video
		// Construct a random identifier using Java's UUID class
		long id =  Long.parseLong(UUID.randomUUID().toString());
		String name = "Bird Juice";
		return new Medication(id, name);
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
