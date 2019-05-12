package es.upm.dit.isst.webLab.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.json.JSONObject;

@Entity
public class Admin {

	@Id
	private String dni;
	
	private String name;
	private String surname;
	private String password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
