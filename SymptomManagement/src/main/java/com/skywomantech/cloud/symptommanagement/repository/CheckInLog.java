package com.skywomantech.cloud.symptommanagement.repository;

public class CheckInLog {
	private long checkinId; // use this for an id to connect to pain and medication logs
	private long created;

	public long getCheckinId() {
		return checkinId;
	}

	public void setCheckinId(long checkinId) {
		this.checkinId = checkinId;
	}
	
	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "CheckInLog [checkinId=" + checkinId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (checkinId ^ (checkinId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CheckInLog))
			return false;
		CheckInLog other = (CheckInLog) obj;
		if (checkinId != other.checkinId)
			return false;
		return true;
	}


	
}
