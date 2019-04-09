package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import es.upm.dit.isst.webLab.model.Doctor;

public interface DoctorDAO {
	public void create(Doctor doctor);
	public Doctor read (Integer id);
	public void update (Doctor doctor);
	public void delete (Doctor doctor);
	public Collection<Doctor> readAll();
}
