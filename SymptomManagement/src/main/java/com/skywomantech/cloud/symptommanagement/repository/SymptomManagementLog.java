package com.skywomantech.cloud.symptommanagement.repository;

import org.springframework.data.annotation.Id;


public class SymptomManagementLog {

	@Id
	private long id;
	
	private long patientId;
	private long created; // auto-generated
	
	public SymptomManagementLog() {
		super();
	}
	public SymptomManagementLog(long patientId) {
		super();
		this.patientId = patientId;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (created ^ (created >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (patientId ^ (patientId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SymptomManagementLog))
			return false;
		SymptomManagementLog other = (SymptomManagementLog) obj;
		if (created != other.created)
			return false;
		if (id != other.id)
			return false;
		if (patientId != other.patientId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SymptomManagementLog [id=" + id + ", patientId=" + patientId
				+ ", created=" + created + "]";
	}

}
