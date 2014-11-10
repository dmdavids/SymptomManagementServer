package com.skywomantech.cloud.symptommanagement.controller;

import groovy.util.logging.Log;

import java.security.Principal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

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
import com.skywomantech.cloud.symptommanagement.repository.MedicationLog;
import com.skywomantech.cloud.symptommanagement.repository.MedicationRepository;
import com.skywomantech.cloud.symptommanagement.repository.Alert;
import com.skywomantech.cloud.symptommanagement.repository.AlertRepository;
import com.skywomantech.cloud.symptommanagement.repository.PainLog;
import com.skywomantech.cloud.symptommanagement.repository.Patient;
import com.skywomantech.cloud.symptommanagement.repository.PatientRepository;
import com.skywomantech.cloud.symptommanagement.repository.Physician;
import com.skywomantech.cloud.symptommanagement.repository.PhysicianRepository;
import com.skywomantech.cloud.symptommanagement.repository.StatusLog;
import com.skywomantech.cloud.symptommanagement.repository.UserCredential;
import com.skywomantech.cloud.symptommanagement.repository.UserCredentialRepository;
import com.skywomantech.cloud.symptommanagement.repository.UserCredential.UserRole;

@Controller
public class SymptomManagementService {

	static final Logger LOG = LoggerFactory
			.getLogger(SymptomManagementService.class);

	@Autowired
	private PatientRepository patients;

	@Autowired
	private PhysicianRepository physicians;

	@Autowired
	private MedicationRepository medications;

	@Autowired
	private AlertRepository alerts;
	
	@Autowired
	private UserCredentialRepository credentials;

	
	// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+  PATIENT APIs =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
	@RequestMapping(value = SymptomManagementApi.PATIENT_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Patient> getPatientList() {
		return patients.findAll();
	}

	@RequestMapping(value = SymptomManagementApi.PATIENT_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.GET)
	public @ResponseBody Patient getPatient(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		return patients.findOne(id);
	}

	@RequestMapping(value = SymptomManagementApi.PATIENT_PATH, method = RequestMethod.POST)
	public @ResponseBody Patient addPatient(@RequestBody Patient patient) {
		Patient savedPatient =  patients.save(patient);
		if (savedPatient != null) {
			// whenever we create a new patient then we add their credentials too
			addCredentials(savedPatient);
		}
		return savedPatient;
	}

	@RequestMapping(value = SymptomManagementApi.PATIENT_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.PUT)
	public @ResponseBody Patient updatePatient(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id,
			@RequestBody Patient patient, Principal principal) {

		LOG.debug("Updating the Patient Records - BEGIN - User is  : " + principal.getName());
		// sort all logs descending by date before saving
		sortStatusLogs(patient);
		sortMedLogs(patient);
		sortPainLogs(patient);

		LOG.debug("Creating alerts for this patient");
		// create any alerts for this patient
		processAlerts(id, patient);

		// save the patient
		LOG.debug("FINAL Saving the patient to storage.");
		Patient savedPatient =  patients.save(patient);
		if (savedPatient != null) {
			if (principal.getName().toLowerCase().equals("admin")) {
				// lets make sure that the physicians are notified of this patient
				// ... design decision was for ADMIN to add physicians to patient 
				// but not visa versa so patient is authority on physicians assigned
				// and we need to keep in sync ... better to do it on server to
				// keep app from a transactional nightmare
				updatePhysicianPatientList(savedPatient);
			} else {
				LOG.info("This user is not admin so we won't bother about the doctor update.");
			}
		}
		return savedPatient;
	}


	@RequestMapping(value = SymptomManagementApi.PATIENT_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.DELETE)
	public @ResponseBody Patient deletePatient(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		Patient found = patients.findOne(id);
		if (found != null) {
			patients.delete(id);
		}
		return found;
	}

	@RequestMapping(value = SymptomManagementApi.PATIENT_SEARCH_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Patient> findByPatientLastName(
			@RequestParam(SymptomManagementApi.NAME_PARAMETER) String lastName) {
		return patients.findByLastName(lastName);
	}

	
	// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+  PHYSICIAN APIS=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
	@RequestMapping(value = SymptomManagementApi.PHYSICIAN_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Physician> getPhysicianList() {
		return physicians.findAll();
	}

	@RequestMapping(value = SymptomManagementApi.PHYSICIAN_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.GET)
	public @ResponseBody Physician getPhysician(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		return physicians.findOne(id);
	}

	@RequestMapping(value = SymptomManagementApi.PHYSICIAN_PATH, method = RequestMethod.POST)
	public @ResponseBody Physician addPhysician(@RequestBody Physician physician) {
		Physician savedPhysician = physicians.save(physician);
		// add login credentials for this new patient
		if (savedPhysician != null) {
			addCredentials(savedPhysician);
		}
		return savedPhysician;
	}

	@RequestMapping(value = SymptomManagementApi.PHYSICIAN_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.PUT)
	public @ResponseBody Physician updatePhysician(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id,
			@RequestBody Physician physician) {
		return physicians.save(physician);
	}

	@RequestMapping(value = SymptomManagementApi.PHYSICIAN_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.DELETE)
	public @ResponseBody Physician deletePhysician(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		Physician found = physicians.findOne(id);
		if (found != null) {
			physicians.delete(id);
		}
		return found;
	}

	@RequestMapping(value = SymptomManagementApi.PHYSICIAN_SEARCH_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Physician> findByPhysicianName(
			@RequestParam(SymptomManagementApi.NAME_PARAMETER) String lastName) {
		return physicians.findByLastName(lastName);
	}

	@RequestMapping(value = SymptomManagementApi.PHYSICIAN_ALERT_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Alert> getPatientAlerts(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		
		// before sending the alerts back, check to see if this doctor already responded to the patient
		// Note: other physicians will still get alerts about this patient only this physician will be turned off
		Collection<Alert> foundAlerts = alerts.findByPhysicianId(id);
		if (foundAlerts != null && foundAlerts.size() > 0) {
			Alert[] alertArray = foundAlerts.toArray(new Alert[foundAlerts.size()]);
			for (Alert a : alertArray) {
				StatusLog s = findPhysicianContactedStatus(a);
				if (s != null) {
					
					LOG.debug("Found a Physician Contact Message post-alert"
							+ "...Removing this alert: " + a.toString());
					foundAlerts.remove(a);
				}
			}
		}
		return foundAlerts;
	}

	@RequestMapping(value = SymptomManagementApi.ALERT_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Alert> getAlertList() {
		return alerts.findAll();
	}

	@RequestMapping(value = SymptomManagementApi.ALERT_PATH, method = RequestMethod.POST)
	public @ResponseBody Alert addAlert(@RequestBody Alert alert) {
		return alerts.save(alert);
	}

	@RequestMapping(value = SymptomManagementApi.ALERT_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.DELETE)
	public @ResponseBody Alert deleteNotification(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		Alert found = alerts.findOne(id);
		if (found != null) {
			alerts.delete(id);
		}
		return found;
	}
	
	// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+  MEDICATION APIS =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

	@RequestMapping(value = SymptomManagementApi.MEDICATION_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Medication> getMedicationList() {
		return medications.findAll();
	}

	@RequestMapping(value = SymptomManagementApi.MEDICATION_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.GET)
	public @ResponseBody Medication getMedication(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		return medications.findOne(id);
	}

	@RequestMapping(value = SymptomManagementApi.MEDICATION_PATH, method = RequestMethod.POST)
	public @ResponseBody Medication addMedication(
			@RequestBody Medication medication) {
		return medications.save(medication);
	}

	@RequestMapping(value = SymptomManagementApi.MEDICATION_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.PUT)
	public @ResponseBody Medication updateMedication(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id,
			@RequestBody Medication medication) {
		medication.setId(id);
		return medications.save(medication);
	}

	@RequestMapping(value = SymptomManagementApi.MEDICATION_SEARCH_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Medication> findByMedicationName(
			@RequestParam(SymptomManagementApi.NAME_PARAMETER) String name) {
		return medications.findByName(name);
	}

	@RequestMapping(value = SymptomManagementApi.MEDICATION_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.DELETE)
	public @ResponseBody Medication deleteMedication(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id) {
		Medication found = medications.findOne(id);
		if (found != null) {
			medications.delete(id);
		}
		return found;
	}
	
	// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+  CREDENTIAL APIS =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
	@RequestMapping(value = SymptomManagementApi.CREDENTIAL_SEARCH_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<UserCredential> findByUserName(
			@RequestParam(SymptomManagementApi.NAME_PARAMETER) String username) {
		// always lowercase the username
		
		// handle the hard-coded admin stuff
		if (username.toLowerCase().contentEquals("admin")) {
			UserCredential cred = new UserCredential();
			cred.setId("");
			cred.setUserId("");
			cred.setUserName(username);
			cred.setUserType(UserRole.ADMIN);
			Collection<UserCredential> creds = new HashSet<UserCredential>();
			creds.add(cred);
			return creds;
		}
		
		return credentials.findByUserName(username.toLowerCase());
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	//  PRIVATE METHODS
	////////////////////////////////////////////////////////////////////////////
	

	private synchronized StatusLog findPhysicianContactedStatus(Alert a) {
		if (a.getPhysicianId() != null) {
			Physician dr = physicians.findOne(a.getPhysicianId());
			if (dr.getPatients() != null) {
				for (Patient p : dr.getPatients()) {
					if (p.getStatusLog() != null && p.getStatusLog().size() > 0) {
						for (StatusLog s : p.getStatusLog()) {
							if (a.getCreated() < s.getCreated()) {
								LOG.debug("found status log turning off the alert.");
								return s;
							}
						}
					}
				}
			}
		}
		LOG.debug("NO physcian contact message found for the alert.");
		return null;
	}
	
	private void updatePhysicianPatientList(Patient patient) {
		Collection<Physician> doctors = patient.getPhysicians();
		if (doctors == null) {
			LOG.debug("This patient has not doctors assigned to them.");
			return;
		}
		
		// make a copy of the patient that only has select fields in it
		Patient clonedPatient = Patient.cloneForPhysician(patient);
		LOG.debug("The Cloned Patient is : " + clonedPatient.toString());
		
		// check all the doctors assigned to the patient and make sure 
		// they have the patient in their lists too
		for (Physician dr : doctors) {
			LOG.debug("Checking this doctor's list : " + dr.getName());
			Physician thisDoctor = physicians.findOne(dr.getId());
			if (thisDoctor != null) { 
				addPatient(thisDoctor, clonedPatient);	
			} else {
				LOG.error("Something fishy! "
						+ "Could not find this doctor by id: " + dr.getId());
				// ignore and keep moving
			}
		} 
	}

	private void addPatient(Physician thisDoctor, Patient clonedPatient) {
		LOG.info("Adding patient to physician list.");
		
		// if no patients assigned to doctor then add this one
		// else find out if its already there and do nothing
		// else add it and save it
		boolean found = false;
		if (thisDoctor.getPatients() == null) {
			thisDoctor.setPatients(new HashSet<Patient>());
			
		} else {  
			LOG.debug("Check to see if the patient is in the doctor's list already.");
			for(Patient p : thisDoctor.getPatients()) {
				if (p.getId() == clonedPatient.getId()) {
					found = true;
					LOG.debug("We found the patient there already. Good to go.");
					break; // its already there so done
				} 
			}	
		}	
		// it's not in the list so add it and update the physician's record
		if (!found) {
			LOG.debug("We are adding this patient to the doctor's list : " + clonedPatient);
			thisDoctor.getPatients().add(clonedPatient);
			Physician checkDr = physicians.save(thisDoctor);
			if (checkDr == null) {
				LOG.error("Physician's updated patient list did not save! Something went wrong!");
			}
		}
	}
	
	private void addCredentials(Patient patient) {
		LOG.debug("Adding CREDENTIALS for new patient : " + patient.toString());
		UserCredential credential = new UserCredential();
		credential.setUserId(patient.getId());
		credential.setPassword("pass");
		credential.setUserName(patient.getUserName().toLowerCase());  // first.last lowercase
		credential.setUserType(UserCredential.UserRole.PATIENT); // auto sets the user role value
		UserCredential saved = credentials.save(credential);
		if (saved == null) {
			LOG.error("ERROR : Credentials did not SAVE!!");
		} else {
			LOG.debug("Credential SAVED is: " + saved.toString());
		}
	}
	
	private void addCredentials(Physician physician) {
		LOG.debug("Adding CREDENTIALS for new physician : " + physician.toString());
		UserCredential credential = new UserCredential();
		credential.setUserId(physician.getId());
		credential.setPassword("pass");
		credential.setUserName(physician.getUserName().toLowerCase());  // first.last lowercase
		credential.setUserType(UserCredential.UserRole.PHYSICIAN);
		UserCredential saved = credentials.save(credential);
		if (saved == null) {
			LOG.error("ERROR : Credentials did not SAVE!!");
		} else {
			LOG.debug("Credential SAVED is: " + saved.toString());
		}
	}

	private void processAlerts(String id, Patient patient) {
		LOG.debug("Processing Alerts for patient :" + patient.toString());

		int deleted = deleteAlertsByPatientId(id); // clear old alerts if any
		LOG.debug("Number of alerts deleted: " + Integer.toString(deleted));
		int severityLevel = CheckForPatientSeverity(patient);
		if (severityLevel > Alert.PAIN_SEVERITY_LEVEL_0 ) {
			// let's make an alert for each doctor so that we can track who
			// knows and who doesn't separately
			LOG.debug("Patient is SEVERE so we are creating alerts for doctors.");
			if (patient.getPhysicians() != null) {
				for (Physician md : patient.getPhysicians()) {
					LOG.debug("Creating alert for Dr. :" + md.toString());
					Alert alert = new Alert();
					alert.setPatientId(id);
					alert.setPhysicianId(md.getId());
					alert.setPatientName(patient.getName());
					alert.setCreated(System.currentTimeMillis());
					alert.setSeverityLevel(severityLevel);
					alerts.save(alert);
				}
			}
		}
	}

	private int deleteAlertsByPatientId(String id) {
		LOG.debug("deleting all Alerts for patient.");
		Collection<Alert> alist = alerts.findAll();
		int count = 0;
		if (alist != null & alist.size() > 0) {
			for (Alert a : alist) {
				if (a.getPatientId().equals(id)) {
					count++;
					alerts.delete(a.getId());
				}
			}
		}
		LOG.debug("Final count of deleted Alerts : " + Integer.toString(count));
		return count;
	}


	// assumes pain logs have descending order for created dates
	private int CheckForPatientSeverity(Patient patient) {
		
		// Create values to check against...negative values are in past
		long sixteenHoursAgo = getHoursFromNow(-16);
		long twelveHoursAgo = getHoursFromNow(-12);
		
		LOG.debug("Checking Severity of Patient : 12 Hours Ago is " + Long.toString(twelveHoursAgo) 
				+ " 16 hours ago is " + Long.toString(sixteenHoursAgo));
		Collection<PainLog> logs = patient.getPainLog();
		if (logs != null && logs.size() > 0) {
			
			// check 12+ hours of severe pain
			LOG.debug("Checking for 12+ hours of severe pain.");
			for (PainLog p : logs) {
				if (p.getSeverity().getValue() < PainLog.Severity.SEVERE.getValue()) {
					LOG.debug("Patient is not SEVERE.");
					break;
				}
				else if (p.getCreated() <= twelveHoursAgo) {
					LOG.debug("Patient has been severe for 12+ hours.");
					patient.setSeverityLevel(Alert.PAIN_SEVERITY_LEVEL_4);
					return Alert.PAIN_SEVERITY_LEVEL_4;
				} else {
					LOG.debug("This log indicates SEVERE checking next one. " + p.toString());
				}
			}

			// check for moderate to severe pain
			LOG.debug("Checking for 16+ hours of moderate to severe pain.");
			for (PainLog p : logs) {
				// patient is OK
				if (p.getSeverity().getValue() < PainLog.Severity.MODERATE.getValue()) {
					LOG.debug("Patient is not MODERATE to SEVERE.");
					break;
				}
				else if (p.getCreated() <= sixteenHoursAgo) {
					LOG.debug("Patient has been MODERATE to SEVERE for 16+ hours");
					patient.setSeverityLevel(Alert.PAIN_SEVERITY_LEVEL_2);
					return Alert.PAIN_SEVERITY_LEVEL_2;
				} else {
					LOG.debug("This log indications MODERATE to SEVERE checking next one. " + p.toString());
				}

			}

			// check for not eating
			LOG.debug("Checking for 12+ hours of not eating.");
			for (PainLog p : logs) {
				// patient is OK
				if (p.getEating().getValue() < PainLog.Eating.NOT_EATING.getValue()) {
					LOG.debug("Patient is EATING.");
					break;
				}
				if (p.getCreated() <= twelveHoursAgo) {
					LOG.debug("Patient has not eaten for 12+ hours.");
					patient.setSeverityLevel(Alert.PAIN_SEVERITY_LEVEL_1);
					return Alert.PAIN_SEVERITY_LEVEL_1;
				}else {
					LOG.debug("This log indications NOT EATING checking next one. " + p.toString());
				}
			}
		} else {
			LOG.debug("There are no pain logs so cannot check severity.");
		}
		return Alert.PAIN_SEVERITY_LEVEL_0;
	}

	public static long getHoursFromNow(int hours) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, hours); // negative hours are in the past
		return cal.getTimeInMillis();
	}

	private void sortPainLogs(Patient patient) {
		LOG.debug("Sorting Pain Logs by reverse creation date");
		Collection<PainLog> logs = patient.getPainLog();
		if (logs == null || logs.size() == 0)
			return;
		PainLogSorter sorter = new PainLogSorter();
		TreeSet<PainLog> sortedLogs = new TreeSet<PainLog>(
				Collections.reverseOrder(sorter));
		for (PainLog p : logs) {
			sortedLogs.add(p);
		}
		patient.setPainLog(sortedLogs);
		
	}

	private void sortStatusLogs(Patient patient) {
		LOG.debug("Sorting Status Logs by reverse creation date");
		Collection<StatusLog> logs = patient.getStatusLog();
		if (logs == null || logs.size() == 0)
			return;
		StatusLogSorter sorter = new StatusLogSorter();
		TreeSet<StatusLog> sortedLogs = new TreeSet<StatusLog>(
				Collections.reverseOrder(sorter));
		for (StatusLog p : logs) {
			sortedLogs.add(p);
		}
		patient.setStatusLog(sortedLogs);
		
	}

	private void sortMedLogs(Patient patient) {
		LOG.debug("Sorting Medication Logs by reverse creation date");
		Collection<MedicationLog> logs = patient.getMedLog();
		if (logs == null || logs.size() == 0)
			return;
		MedLogSorter sorter = new MedLogSorter();
		TreeSet<MedicationLog> sortedLogs = new TreeSet<MedicationLog>(
				Collections.reverseOrder(sorter));
		for (MedicationLog p : logs) {
			sortedLogs.add(p);
		}
		patient.setMedLog(sortedLogs);
		
	}

	private class PainLogSorter implements Comparator<PainLog> {

		public int compare(PainLog x, PainLog y) {
			return Long.compare(x.getCreated(), y.getCreated());
		}
	}

	private class MedLogSorter implements Comparator<MedicationLog> {

		public int compare(MedicationLog x, MedicationLog y) {
			return Long.compare(x.getCreated(), y.getCreated());
		}
	}

	private class StatusLogSorter implements Comparator<StatusLog> {

		public int compare(StatusLog x, StatusLog y) {
			return Long.compare(x.getCreated(), y.getCreated());
		}
	}

}
