package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import es.upm.dit.isst.webLab.model.Specialty;

public interface SpecialtyDAO {
	public void create(Specialty spec);
	public Specialty read (String id);
	public void update (Specialty spec);
	public void delete (Specialty app);
	public Collection<Specialty> readAll();
}
