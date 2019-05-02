package es.upm.dit.isst.webLab.model;

import java.io.Serializable;
import org.json.JSONObject;

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
	
	public JSONObject toJSON() {
	  	//create Json Object
	  	JSONObject json = new JSONObject();

	    // put some value pairs into the JSON object .
	    json.put("date", super.getDate());
	    json.put("start_time", super.getStart_time());
	    json.put("patient_name", this.patient.getName());
	    json.put("patient_surname", this.patient.getSurname());
	    json.put("presence", super.getPresence());
	    json.put("app_id", super.id);
	    
	    
	    
		return json;
	}

	
}
