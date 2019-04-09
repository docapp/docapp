package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import es.upm.dit.isst.webLab.model.Patient;

public interface PatientDAO {
	public void create(Patient patient);
	public Patient read (Integer id);
	public void update (Patient patient);
	public void delete (Patient patient);
	public Collection<Patient> readAll();
}
