package es.upm.dit.isst.webLab.model;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.json.JSONObject;
import org.json.JSONArray;

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
	
	public JSONObject toJSON() {
  	  //create Json Object
  	  JSONObject json = new JSONObject();

	    // put some value pairs into the JSON object .
	    json.put("id", this.id);
	    json.put("name", this.name);
	    json.put("description", this.description);
	    
	    Collection<Doctor> docs = this.doctors;
    	    	
	    JSONArray array = new JSONArray();
    	//String outTest = "[";

    	for(Doctor d : docs) {
    		array.put(d.toJSON());
    	}
    	
    	
	    json.put("doctors", array);
	
		return json;
	}
	

	
}