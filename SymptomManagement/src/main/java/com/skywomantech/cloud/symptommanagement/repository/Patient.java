package com.skywomantech.cloud.symptommanagement.repository;

import java.util.Set;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Patient {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String birthdate;
	private long lastLogin;
	private Boolean active;
	private int severityLevel;
	
	private PatientPrefs prefs;
	private Set<Medication> prescriptions;
	private Set<Physician> physicians;
	
	private Set<PainLog> painLog;
	private Set<MedicationLog> medLog;
	private Set<StatusLog> statusLog;

	public Patient() {
		super();
	}
	
	public Patient( String firstName, String lastName ){
		super();
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
		this.birthdate = "";
		this.active = true;
		this.lastLogin = 0L;
		this.severityLevel = 0;
	}
	
	
	public Patient( String firstName, String lastName, String birthdate, Boolean severe, 
			long lastLogin,
			Boolean isActive, Set<Medication> prescriptions,
			Set<Physician> physicians, Set<PainLog> painLog,
			Set<MedicationLog> medLog, Set<StatusLog> statusLog, PatientPrefs prefs) {
		super();
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
		this.birthdate = birthdate;
		this.severityLevel = 0;
		this.lastLogin = lastLogin;
		this.active = isActive;
		this.prescriptions = prescriptions;
		this.physicians = physicians;
		this.painLog = painLog;
		this.medLog = medLog;
		this.statusLog = statusLog;
		this.prefs = prefs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
    public String getName() {
        String name = "";
        if (firstName != null && !firstName.isEmpty()) name += firstName;
        if (!name.isEmpty()) name += " ";
        if (lastName != null  && !lastName.isEmpty()) name+= lastName;
        return name;
    }

	@JsonIgnore
    public String getUserName() {
        String name = "";
        if (firstName != null && !firstName.isEmpty()) name += firstName;
        if (!name.isEmpty()) name += ".";
        if (lastName != null  && !lastName.isEmpty()) name+= lastName;
        return name;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Boolean isActive() {
		return active;
	}

	public void setIsActive(Boolean isActive) {
		this.active = isActive;
	}

	public int getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(int severityLevel) {
		this.severityLevel = severityLevel;
	}

	public Set<Medication> getPrescriptions() {
		return prescriptions;
	}
	
	public void setPrescriptions(Set<Medication> prescriptions) {
		this.prescriptions = prescriptions;
	}
	
	public Set<Physician> getPhysicians() {
		return physicians;
	}
	
	public void setPhysicians(Set<Physician> physicians) {
		this.physicians = physicians;
	}
	
	public Set<PainLog> getPainLog() {
		return painLog;
	}
	
	public void setPainLog(Set<PainLog> painLog) {
		this.painLog = painLog;
	}
	
	public Set<MedicationLog> getMedLog() {
		return medLog;
	}
	
	public void setMedLog(Set<MedicationLog> medLog) {
		this.medLog = medLog;
	}
	
	public Set<StatusLog> getStatusLog() {
		return statusLog;
	}
	
	public void setStatusLog(Set<StatusLog> statusLog) {
		this.statusLog = statusLog;
	}

	public PatientPrefs getPrefs() {
		return prefs;
	}

	public void setPrefs(PatientPrefs prefs) {
		this.prefs = prefs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Patient))
			return false;
		Patient other = (Patient) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", birthdate=" + birthdate + ", severityLevel=" + severityLevel 
				+ ", lastLogin="
				+ lastLogin + ", active=" + active + ", prescriptions="
				+ prescriptions + ", physicians=" + physicians + ", painLog="
				+ painLog + ", medLog=" + medLog + ", statusLog=" + statusLog
				+ ", prefs=" + prefs + "]";
	}

	/**
	 * We only want the patient id, name and birthdate 
	 * to be put in the physician's record so we don't just copy all 
	 * the other non-essential volatile stuff
	 * 
	 * @param p patient to copy
	 * @return copy of p with only name, id, and birthdate
	 */
	public static synchronized Patient cloneForPhysician(Patient p) {
		Patient p2 = new Patient();
		p2.setId(p.getId());
		p2.setFirstName(p.getFirstName());
		p2.setLastName(p.getLastName());
		p2.setBirthdate(p.getBirthdate());
		return p2;
		
	}
	
}
