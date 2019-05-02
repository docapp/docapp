package es.upm.dit.isst.webLab.model;

import java.io.Serializable;

public class AppointmentAndPatient extends Appointment implements Serializable{

	private Patient patient;
	
	public AppointmentAndPatient () {
		
		super();
		
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
}
