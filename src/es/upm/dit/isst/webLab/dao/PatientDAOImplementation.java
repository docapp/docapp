package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.upm.dit.isst.webLab.model.Doctor;
import es.upm.dit.isst.webLab.model.Patient;

public class PatientDAOImplementation implements PatientDAO{
	

	private static  PatientDAOImplementation instance = null;
	private PatientDAOImplementation() {}
	public static PatientDAOImplementation getInstance() {
		if( null == instance ) {
			 instance = new PatientDAOImplementation();
		  }
		  return instance;
	}

	@Override
	public void create(Patient patient) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save(patient);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Patient read(String dni) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Patient patient = session.load(Patient.class, dni);
		session.getTransaction().commit();
		session.close();
		return patient;
	}

	@Override
	public void update(Patient patient) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(patient);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void delete(Patient patient) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(patient);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public Collection<Patient> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Collection<Patient> patients = session.createQuery("from Patient").list();
		session.getTransaction().commit();
		session.close();
		return patients;
	}

}
