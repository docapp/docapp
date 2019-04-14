package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.upm.dit.isst.webLab.model.Admin;
import es.upm.dit.isst.webLab.model.Patient;

public class AdminDAOImplementation implements AdminDAO{
	

	private static  AdminDAOImplementation instance = null;
	private AdminDAOImplementation() {}
	public static AdminDAOImplementation getInstance() {
		if( null == instance ) {
			 instance = new AdminDAOImplementation();
		  }
		return instance;
	}

	@Override
	public void create(Admin admin) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save(admin);
		session.getTransaction().commit();
		session.close();		
	}

	@Override
	public Admin read(String dni) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Admin admin = session.load(Admin.class, dni);
		session.getTransaction().commit();
		session.close();
		return admin;
	}

	@Override
	public void update(Admin admin) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(admin);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void delete(Admin admin) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(admin);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public Collection<Admin> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Collection<Admin> admins = session.createQuery("from Admin").list();
		session.getTransaction().commit();
		session.close();
		return admins;
	}

	

}
