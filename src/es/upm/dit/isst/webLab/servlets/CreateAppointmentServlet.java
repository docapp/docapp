package es.upm.dit.isst.webLab.servlets;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.upm.dit.isst.webLab.dao.DoctorDAO;
import es.upm.dit.isst.webLab.dao.DoctorDAOImplementation;
import es.upm.dit.isst.webLab.dao.PatientDAO;
import es.upm.dit.isst.webLab.dao.PatientDAOImplementation;
import es.upm.dit.isst.webLab.dao.AppointmentDAO;
import es.upm.dit.isst.webLab.dao.AppointmentDAOImplementation;
import es.upm.dit.isst.webLab.model.Doctor;
import es.upm.dit.isst.webLab.model.Patient;
import es.upm.dit.isst.webLab.model.TimeSlot;
import es.upm.dit.isst.webLab.model.Appointment;

@WebServlet("/CreateAppointmentServlet")
public class CreateAppointmentServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String doc_dni= req.getParameter( "doc_dni" );
		String pat_dni = req.getParameter( "pat_dni" );
		java.sql.Date date = Date.valueOf(req.getParameter( "date" ));
		String t = req.getParameter("start_time");

		TimeSlot timeSlot = new TimeSlot();
		Integer time = timeSlot.getTimeSlotKey(t);
		
		System.out.println(doc_dni);
		System.out.println(pat_dni);
		System.out.println(date);
		System.out.println(time);




		DoctorDAO ddao = DoctorDAOImplementation.getInstance();
		Doctor doctor = ddao.read(doc_dni);

		PatientDAO pdao = PatientDAOImplementation.getInstance();
		Patient patient = pdao.read(pat_dni);
		
		Appointment app = new Appointment();
		app.setApp_doctor(doctor);
		app.setApp_patient(patient);
		app.setDate(date);
		app.setStart_time(time);
		
		AppointmentDAO adao = AppointmentDAOImplementation.getInstance();
		adao.create( app );
		
		resp.sendRedirect( req.getContextPath() + "/PatientServlet?pat_dni=" + pat_dni );
	}
}
