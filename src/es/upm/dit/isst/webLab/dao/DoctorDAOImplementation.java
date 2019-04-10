package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.upm.dit.isst.webLab.model.Doctor;

public class DoctorDAOImplementation implements DoctorDAO{
	
	private static  DoctorDAOImplementation instance = null;
	private DoctorDAOImplementation() {}
	public static DoctorDAOImplementation getInstance() {
		if( null == instance ) {
			 instance = new DoctorDAOImplementation();
		  }
		  return instance;
	}

	@Override
	public void create(Doctor doctor) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save(doctor);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public Doctor read(String dni) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Doctor doctor = session.load(Doctor.class, dni);
		session.getTransaction().commit();
		session.close();
		return doctor;
	}

	@Override
	public void update(Doctor doctor) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(doctor);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void delete(Doctor doctor) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(doctor);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public Collection<Doctor> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Collection<Doctor> doctors = session.createQuery("from Doctor").list();
		session.getTransaction().commit();
		session.close();
		return doctors;
	}

}
