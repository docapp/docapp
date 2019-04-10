package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.upm.dit.isst.webLab.model.Appointment;
import es.upm.dit.isst.webLab.model.Specialty;

public class SpecialtyDAOImplementation implements SpecialtyDAO{

	
	private static  SpecialtyDAOImplementation instance = null;
	private SpecialtyDAOImplementation() {}
	public static SpecialtyDAOImplementation getInstance() {
	  if( null == instance ) {
		 instance = new SpecialtyDAOImplementation();
	  }
	  return instance;
	}
	
	@Override
	public void create(Specialty spec) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save(spec);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public Specialty read (String id){
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Specialty spec = session.load(Specialty.class, id);
		session.getTransaction().commit();
		session.close();
		return spec;

	}
	
	@Override
	public void update (Specialty spec){
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(spec);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public void delete (Specialty spec) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(spec);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public Collection<Specialty> readAll(){
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Collection<Specialty> specs = session.createQuery("from Specialty").list();
		session.getTransaction().commit();
		session.close();
		return specs;
	}

}
