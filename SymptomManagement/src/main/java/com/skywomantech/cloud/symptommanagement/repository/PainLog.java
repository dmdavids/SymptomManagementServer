package com.skywomantech.cloud.symptommanagement.repository;


public class PainLog extends SymptomManagementLog {
	
	private int severity;
	private int eating;

	public PainLog() {
		super();
	}
	
	public PainLog(int patientId, int severity, int eating) {
		super(patientId);
		this.severity = severity;
		this.eating = eating;
	}
	
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	public int getEating() {
		return eating;
	}
	public void setEating(int eating) {
		this.eating = eating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + eating;
		result = prime * result + severity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof PainLog))
			return false;
		PainLog other = (PainLog) obj;
		if (eating != other.eating)
			return false;
		if (severity != other.severity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PainLog [severity=" + severity + ", eating=" + eating
				+ ", toString()=" + super.toString() + "]";
	}
	
}
