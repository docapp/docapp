package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import es.upm.dit.isst.webLab.model.Admin;
import es.upm.dit.isst.webLab.model.Patient;

public interface AdminDAO {
	public void create(Admin admin);
	public Admin read (String dni);
	public void update (Admin admin);
	public void delete (Admin admin);
	public Collection<Admin> readAll();
}
