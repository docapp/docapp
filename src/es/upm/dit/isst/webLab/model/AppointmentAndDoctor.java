package es.upm.dit.isst.webLab.model;

import java.io.Serializable;

public class AppointmentAndDoctor extends Appointment implements Serializable{

	private Doctor doctor;
	
	public AppointmentAndDoctor () {
		
		super();
		
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	
}