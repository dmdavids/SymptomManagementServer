package com.skywomantech.cloud.symptommanagement.testdata;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skywomantech.cloud.symptommanagement.repository.Medication;
import com.skywomantech.cloud.symptommanagement.repository.MedicationLog;
import com.skywomantech.cloud.symptommanagement.repository.PainLog;
import com.skywomantech.cloud.symptommanagement.repository.PainLog.Eating;
import com.skywomantech.cloud.symptommanagement.repository.PainLog.Severity;
import com.skywomantech.cloud.symptommanagement.repository.Patient;
import com.skywomantech.cloud.symptommanagement.repository.Physician;
import com.skywomantech.cloud.symptommanagement.repository.StatusLog;


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
	
	private static long randomDate = System.currentTimeMillis();
			
	
	/**
	 * Construct and return a Patient object with a
	 * random name and password
	 * 
	 * @return
	 */
	public static Patient randomPatient(String first, String last, String birthday) {
		Patient p = new Patient();
		p.setFirstName(first);
		p.setLastName(last);
		p.setBirthdate(birthday);
		return p;
	}
	
	
	public static Physician randomPhysician(String first, String last) {
		Physician p = new Physician();
		p.setFirstName(first);
		p.setLastName(last);
		return p;
	}
	
	
	public static Medication randomMedication(String name) {
		Medication m = new Medication(name);
		return m;
	}
	
	
	public static PainLog randomPainLog() {
		PainLog log = new PainLog();
		randomDate = getHoursFromNow(randomDate, 6);
		log.setCreated(randomDate);
		log.setSeverity(getRandomSeverity());
		log.setEating(getRandomEating());
		return log;
	}
	
	private static Eating getRandomEating() {
		int r = randInt(1, 3);
		r*= 100;
		return Eating.findByValue(r);
	}


	private static Severity getRandomSeverity() {
		int r = randInt(1, 3);
		r*= 100;
		return Severity.findByValue(r);
	}

	public static MedicationLog randomMedLog(Medication med) {
		MedicationLog log = new MedicationLog();
		randomDate = getHoursFromNow(randomDate, 1);
		log.setCreated(randomDate);
		log.setMed(med);
		return log;
	}

	public static Patient addPhysicianToPatient(Physician dr, Patient p){
		if (p.getPhysicians() == null ) {
			p.setPhysicians(new HashSet<Physician>());
		}
		p.getPhysicians().add(dr);
		return p;
	}
	
	public static Patient addPrescriptionToPatient(Medication m, Patient p) {
		if (p.getPrescriptions() == null) {
			p.setPrescriptions(new HashSet<Medication>());
		}
		p.getPrescriptions().add(m);
		return p;
	}
	
	public static Patient addPainLogToPatient(PainLog log, Patient p) {
		if(p.getPainLog() == null) {
			p.setPainLog(new HashSet<PainLog>());
		}
		p.getPainLog().add(log);
		return p;
	}
	
	public static Patient addMedLogToPatient(MedicationLog log, Patient p) {
		if(p.getMedLog() == null) {
			p.setMedLog(new HashSet<MedicationLog>());
		}
		p.getMedLog().add(log);
		return p;
	}
	
	public static Patient addStatusLogToPatient(StatusLog log, Patient p) {
		if(p.getStatusLog() == null) {
			p.setStatusLog(new HashSet<StatusLog>());
		}
		p.getStatusLog().add(log);
		return p;
	}
	
	public static long getHoursFromNow(long date, int hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date);
		cal.add(Calendar.HOUR, hours); // negative hours are in the past
		return cal.getTimeInMillis();
	}

	
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
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
