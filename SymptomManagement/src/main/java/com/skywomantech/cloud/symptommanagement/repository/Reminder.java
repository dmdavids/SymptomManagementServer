package com.skywomantech.cloud.symptommanagement.repository;

import java.math.BigInteger;

public class Reminder {
	
	BigInteger Id;
	private String name;
	private int dayOfWeek;
	private int hour;
	private int minutes;
	private String alarm;
	private boolean on;
	private long created;
	
	public enum ReminderType {
		PAIN(1), MED(2), GENERIC(3);

		private final int value;

		private ReminderType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	};
	
	private ReminderType reminderType; 

	
	public Reminder() {
		super();
	}
	
	public Reminder(String name, int dayOfWeek, int hour, int minutes, String alarm) {
		super();
		this.name = name;
		this.dayOfWeek = dayOfWeek;
		this.hour = hour;
		this.minutes = minutes;
		this.alarm = alarm;
	}
	
	public BigInteger getId() {
		return Id;
	}
	public void setId(BigInteger id) {
		Id = id;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public String getAlarm() {
		return alarm;
	}
	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean isOn) {
		this.on = on;
	}

	public ReminderType getReminderType() {
		return reminderType;
	}

	public void setReminderType(ReminderType reminderType) {
		this.reminderType = reminderType;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reminder))
			return false;
		Reminder other = (Reminder) obj;
		if (created != other.created)
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Reminder{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", dayOfWeek=" + dayOfWeek +
                ", hour=" + hour +
                ", minutes=" + minutes +
                ", alarm='" + alarm + '\'' +
                ", on=" + on +
                ", created=" + created +
                ", reminderType=" + reminderType +
                '}';
    }


	
}
