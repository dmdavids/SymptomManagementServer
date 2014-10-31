package com.skywomantech.cloud.symptommanagement.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

// TODO: Before saving make sure that objects are unique in appropriate fields
// TODO: Before saving or updating do field validation (e.g. correctness, uniqueness, etc.)
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
		LOG.debug("Adding this patient : " + patient);
		// new patient should not have any logs to process yet
		return patients.save(patient);
	}

	@RequestMapping(value = SymptomManagementApi.PATIENT_PATH
			+ SymptomManagementApi.ID_PATH, method = RequestMethod.PUT)
	public @ResponseBody Patient updatePatient(
			@PathVariable(SymptomManagementApi.ID_PARAMETER) String id,
			@RequestBody Patient patient) {

		 // sort all logs descending by date before saving
		 sortStatusLogs(patient);
		 sortMedLogs(patient);
		 sortPainLogs(patient);
		
		 // create any alerts for this patient
		 processAlerts(id, patient);
		
		 //save the patient
		return patients.save(patient);
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
		return physicians.save(physician);
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
		return alerts.findByPhysicianId(id);
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

	private void processAlerts(String id, Patient patient) {
		LOG.debug("Processing Alerts for patient :" + patient.toString());

		int deleted = deleteAlertsByPatientId(id); // clear old alerts if any
		LOG.debug("Number of alerts deleted: " + Integer.toString(deleted));
		if (isPatientSevere(patient)) {
			// let's make an alert for each doctor so that we can track who
			// knows and who doesn't separately
			LOG.debug("Patient is SEVERE so we are creating alerts for doctors.");
			for (Physician md : patient.getPhysicians()) {
				LOG.debug("Creating alert for Dr. :" + md.toString());
				Alert alert = new Alert();
				alert.setPatientId(id);
				alert.setPhysicianId(md.getId());
				alert.setPatientName(patient.getName());
				alert.setCreated(System.currentTimeMillis());
				alerts.save(alert);
			}
		}
	}

	private int deleteAlertsByPatientId(String id) {
		LOG.debug("deleting all Alerts for patient.");
		Collection<Alert> alist = alerts.findAll();
		int count = 0;
		for (Alert a : alist) {
			if (a.getPatientId().equals(id)) {
				count++;
				alerts.delete(a.getId());
			}
		}
		return count;
	}

	// assumes pain logs have descending order for created dates
	private boolean isPatientSevere(Patient patient) {
		// negative values are in past
		long sixteenHoursAgo = getHoursFromNow(-16);
		long twelveHoursAgo = getHoursFromNow(-12);

		// check 12+ hours of severe pain
		for (PainLog p : patient.getPainLog()) {
			if (p.getSeverity().getValue() < PainLog.Severity.SEVERE.getValue())
				break;
			else if (p.getCreated() >= twelveHoursAgo) {
				LOG.debug("Patient has been severe for 12+ hours.");
				return true;
			}
		}

		// check for moderate to severe pain
		for (PainLog p : patient.getPainLog()) {
			// patient is OK
			if (p.getSeverity().getValue() < PainLog.Severity.MODERATE
					.getValue())
				break;
			else if (p.getCreated() >= sixteenHoursAgo) {
				LOG.debug("Patient has been MODERATE to SEVERE for 16+ hours");
				return true;
			}

		}

		// check for not eating
		for (PainLog p : patient.getPainLog()) {
			// patient is OK
			if (p.getEating().getValue() < PainLog.Eating.NOT_EATING.getValue())
				break;
			if (p.getCreated() >= twelveHoursAgo) {
				LOG.debug("Patient has not eaten for 12+ hours.");
				return true;
			}
		}

		return false;
	}

	public static long getHoursFromNow(int hours) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, hours); // negative hours are in the past
		return cal.getTimeInMillis();
	}

	private void sortPainLogs(Patient patient) {
		Collection<PainLog> logs = patient.getPainLog();
		PainLogSorter sorter = new PainLogSorter();
		TreeSet<PainLog> sortedLogs = new TreeSet<PainLog>(
				Collections.reverseOrder(sorter));
		for (PainLog p : logs) {
			sortedLogs.add(p);
		}
		patient.setPainLog(sortedLogs);
		LOG.debug("Sorted Pain Logs :" + sortedLogs.toString());
	}

	private void sortStatusLogs(Patient patient) {
		Collection<StatusLog> logs = patient.getStatusLog();
		StatusLogSorter sorter = new StatusLogSorter();
		TreeSet<StatusLog> sortedLogs = new TreeSet<StatusLog>(
				Collections.reverseOrder(sorter));
		for (StatusLog p : logs) {
			sortedLogs.add(p);
		}
		patient.setStatusLog(sortedLogs);
		LOG.debug("Sorted Status Logs :" + sortedLogs.toString());
	}

	private void sortMedLogs(Patient patient) {
		Collection<MedicationLog> logs = patient.getMedLog();
		MedLogSorter sorter = new MedLogSorter();
		TreeSet<MedicationLog> sortedLogs = new TreeSet<MedicationLog>(
				Collections.reverseOrder(sorter));
		for (MedicationLog p : logs) {
			sortedLogs.add(p);
		}
		patient.setMedLog(sortedLogs);
		LOG.debug("Sorted Medication Logs :" + sortedLogs.toString());
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
