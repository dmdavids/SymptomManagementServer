package com.skywomantech.cloud.symptommanagement.repository;

import java.util.Arrays;

public class Physician extends User {

	private StatusLog[] logs;
	
	public Physician() {
		super();
	}
	
	public Physician(long id, String name, String password, long lastLogin) {
		super(id, name, password, lastLogin);
	}
	
	public Physician(String name, String password, StatusLog[] logs) {
		super(name, password);
		this.logs = logs;
	}

	public StatusLog[] getLogs() {
		return logs;
	}
	public void setLogs(StatusLog[] logs) {
		this.logs = logs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(logs);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Physician))
			return false;
		Physician other = (Physician) obj;
		if (!Arrays.equals(logs, other.logs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Physician [logs=" + Arrays.toString(logs) + ", toString()="
				+ super.toString() + "]";
	}
	
}
