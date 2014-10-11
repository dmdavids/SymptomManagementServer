package com.skywomantech.cloud.symptommanagement.repository;

import java.math.BigInteger;
import java.util.Set;

import org.springframework.data.annotation.Id;


public class Patient {

	@Id
	private BigInteger id;
	private String name;
	private long lastLogin;
	private Boolean isActive;
	
	private Set<Medication> prescriptions;
	private Set<Physician> physicians;
	
	private Set<PainLog> painLog;
	private Set<MedicationLog> medLog;
	private Set<StatusLog> statusLog;
	
	public Patient() {
		super();
	}
	
	public Patient( String name ){
		super();
		this.name = name;
		this.isActive = true;
		this.lastLogin = 0L;
	}
	
	public Patient( String name, long lastLogin,
			Boolean isActive, Set<Medication> prescriptions,
			Set<Physician> physicians, Set<PainLog> painLog,
			Set<MedicationLog> medLog, Set<StatusLog> statusLog) {
		super();
		this.name = name;
		this.lastLogin = lastLogin;
		this.isActive = isActive;
		this.prescriptions = prescriptions;
		this.physicians = physicians;
		this.painLog = painLog;
		this.medLog = medLog;
		this.statusLog = statusLog;
	}


	public BigInteger getId() {
		return id;
	}


	public void setId(BigInteger id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}


	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + (int) (lastLogin ^ (lastLogin >>> 32));
		result = prime * result + ((medLog == null) ? 0 : medLog.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((painLog == null) ? 0 : painLog.hashCode());
		result = prime * result
				+ ((physicians == null) ? 0 : physicians.hashCode());
		result = prime * result
				+ ((prescriptions == null) ? 0 : prescriptions.hashCode());
		result = prime * result
				+ ((statusLog == null) ? 0 : statusLog.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Patient))
			return false;
		Patient other = (Patient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (lastLogin != other.lastLogin)
			return false;
		if (medLog == null) {
			if (other.medLog != null)
				return false;
		} else if (!medLog.equals(other.medLog))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (painLog == null) {
			if (other.painLog != null)
				return false;
		} else if (!painLog.equals(other.painLog))
			return false;
		if (physicians == null) {
			if (other.physicians != null)
				return false;
		} else if (!physicians.equals(other.physicians))
			return false;
		if (prescriptions == null) {
			if (other.prescriptions != null)
				return false;
		} else if (!prescriptions.equals(other.prescriptions))
			return false;
		if (statusLog == null) {
			if (other.statusLog != null)
				return false;
		} else if (!statusLog.equals(other.statusLog))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", lastLogin="
				+ lastLogin + ", isActive=" + isActive + ", prescriptions="
				+ prescriptions + ", physicians=" + physicians + ", painLog="
				+ painLog + ", medLog=" + medLog + ", statusLog=" + statusLog
				+ "]";
	}
	
}
