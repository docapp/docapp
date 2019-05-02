package es.upm.dit.isst.webLab.model;

import java.io.Serializable;

import org.json.JSONObject;

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

	public JSONObject toJSON() {
	  	//create Json Object
	  	JSONObject json = new JSONObject();

	    // put some value pairs into the JSON object .
	    json.put("date", super.getDate());
	    json.put("start_time", super.getStart_time());
	    json.put("doctor_name", this.doctor.getName());
	    json.put("doctor_surname", this.doctor.getSurname());
	    json.put("presence", super.getPresence());
	    json.put("app_id", super.getId());
	    
		return json;
	}
}