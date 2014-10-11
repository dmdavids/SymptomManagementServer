package com.skywomantech.cloud.symptommanagement.repository;

import org.springframework.data.annotation.Id;

import com.google.common.base.Objects;


public class Medication {
	
	@Id
	private long id;
	private String name;
	
	public Medication() {
	}
	
	public Medication(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Medication(String name) {
		super();
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Medication){
			Medication other = (Medication) obj;
			return Objects.equal(name, other.name);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Medication [id=" + id + ", name=" + name + "]";
	}
	
}
