package es.upm.dit.isst.webLab.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.json.JSONObject;

@Entity
public class Doctor implements Serializable {

	@Id
	private String dni;
	
	private String name;
	private String surname;
	private String specialty;
	private String password;


	@OneToMany(mappedBy = "app_doctor", fetch = FetchType.EAGER)
	private Collection<Appointment> appointments;

	public Doctor() {
		
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Collection<Appointment> appointments) {
		this.appointments = appointments;
	}	
	
	public JSONObject toJSON() {
	  	  //create Json Object
	  	  JSONObject json = new JSONObject();

		    // put some value pairs into the JSON object .
		    json.put("dni", this.dni);
		    json.put("name", this.name);
		    json.put("surname", this.surname);
		    
			return json;
		}
}


