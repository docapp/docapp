package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import es.upm.dit.isst.webLab.model.Appointment;

public interface AppointmentDAO {
	public void create(Appointment app);
	public Appointment read (Integer id);
	public void update (Appointment app);
	public void delete (Appointment app);
	public Collection<Appointment> readAll();
	
}