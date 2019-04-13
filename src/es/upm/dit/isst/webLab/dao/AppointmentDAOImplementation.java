package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.upm.dit.isst.webLab.dao.SessionFactoryService;

import es.upm.dit.isst.webLab.model.Appointment;

public class AppointmentDAOImplementation implements AppointmentDAO {
	
	private static  AppointmentDAOImplementation instance = null;
	private AppointmentDAOImplementation() {}
	public static AppointmentDAOImplementation getInstance() {
	  if( null == instance ) {
		 instance = new AppointmentDAOImplementation();
	  }
	  return instance;
	}
	
	@Override
	public void create(Appointment app) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save(app);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public Appointment read (Integer id){
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Appointment app = session.load(Appointment.class, id);
		session.getTransaction().commit();
		session.close();
		return app;

	}
	
	@Override
	public void update (Appointment app){
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(app);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public void delete (Appointment app) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(app);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public Collection<Appointment> readAll(){
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Collection<Appointment> apps = session.createQuery("from Appointment").list();
		session.getTransaction().commit();
		session.close();
		return apps;
	}
	@Override
	public Collection<Appointment> filterDateDoctor(String doc_dni, java.sql.Date date) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Collection<Appointment> apps = session.createQuery("from Appointment where app_doctor_dni='" + doc_dni + "' and date='" + date + "'").list();
		session.getTransaction().commit();
		session.close();
		return apps;
	}
	

}
