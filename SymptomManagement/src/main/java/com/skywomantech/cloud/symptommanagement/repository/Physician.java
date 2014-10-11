package com.skywomantech.cloud.symptommanagement.repository;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.data.annotation.Id;


public class Physician {

	@Id
	private BigInteger id;
	private String name;
	private Collection<Patient> patients;
	
	
	public Physician() {
		super();
	}

	public Physician(String name) {
		super();
		this.name = name;
		this.patients = null;
	}

	public Physician(String name, Collection<Patient> patients) {
		super();
		this.name = name;
		this.patients = patients;
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


	public Collection<Patient> getPatients() {
		return patients;
	}


	public void setPatients(Collection<Patient> patients) {
		this.patients = patients;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((patients == null) ? 0 : patients.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Physician))
			return false;
		Physician other = (Physician) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (patients == null) {
			if (other.patients != null)
				return false;
		} else if (!patients.equals(other.patients))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Physician [id=" + id + ", name=" + name + ", patients="
				+ patients + "]";
	}


	
}
