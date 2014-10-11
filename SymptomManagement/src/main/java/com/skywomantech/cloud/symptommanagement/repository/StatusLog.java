package com.skywomantech.cloud.symptommanagement.repository;


public class StatusLog extends SymptomManagementLog {

	private int physicianId;  // can be null only entered if status is by dr.
	private String note;
	private String image_location;
	
	public StatusLog() {
		super();
	}
	
	public StatusLog(int patientId, long created, 
			int physicianId, String note, String image_location) {
		super(patientId);
		this.physicianId = physicianId;
		this.note = note;
		this.image_location = image_location;
	}
	
	public int getPhysicianId() {
		return physicianId;
	}
	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getImage_location() {
		return image_location;
	}
	public void setImage_location(String image_location) {
		this.image_location = image_location;
	}

}
