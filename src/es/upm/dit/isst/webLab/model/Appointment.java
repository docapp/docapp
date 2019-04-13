package es.upm.dit.isst.webLab.model;

import java.io.Serializable;
import java.sql.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import es.upm.dit.isst.webLab.model.Doctor;

@Entity
public class Appointment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date date;
	private Integer start_time;
	
	
	@ManyToOne
	private Doctor app_doctor;
	
	@ManyToOne
	private Patient app_patient;

	public Appointment() {
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getStart_time() {
		return start_time;
	}

	public void setStart_time(Integer start_time) {
		this.start_time = start_time;
	}

	public Doctor getApp_doctor() {
		return app_doctor;
	}

	public void setApp_doctor(Doctor app_doctor) {
		this.app_doctor = app_doctor;
	}

	public Patient getApp_patient() {
		return app_patient;
	}

	public void setApp_patient(Patient app_patient) {
		this.app_patient = app_patient;
	}

	
}

