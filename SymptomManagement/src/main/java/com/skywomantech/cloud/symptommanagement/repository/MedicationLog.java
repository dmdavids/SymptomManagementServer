package com.skywomantech.cloud.symptommanagement.repository;

public class MedicationLog extends SymptomManagementLog {

	private long prescriptionId;
	private long taken;

	public MedicationLog() {
		super();
	}

	public MedicationLog(long patientId, long prescriptionId, long taken) {
		super(patientId);
		this.prescriptionId = prescriptionId;
		this.taken = taken;
	}

	public long getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public long getTaken() {
		return taken;
	}

	public void setTaken(long taken) {
		this.taken = taken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (prescriptionId ^ (prescriptionId >>> 32));
		result = prime * result + (int) (taken ^ (taken >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof MedicationLog))
			return false;
		MedicationLog other = (MedicationLog) obj;
		if (prescriptionId != other.prescriptionId)
			return false;
		if (taken != other.taken)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MedicationLog [prescriptionId=" + prescriptionId + ", taken="
				+ taken + ", toString()=" + super.toString() + "]";
	}

}
