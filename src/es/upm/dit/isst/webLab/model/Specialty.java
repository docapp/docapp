package es.upm.dit.isst.webLab.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Specialty implements Serializable {

	@Id
	private String id;
	
	private String name;
	private String description;



	@OneToMany(mappedBy = "specialty", fetch = FetchType.EAGER)
	private Collection<Doctor> doctors;

	public Specialty() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Collection<Doctor> doctors) {
		this.doctors = doctors;
	}


	
}